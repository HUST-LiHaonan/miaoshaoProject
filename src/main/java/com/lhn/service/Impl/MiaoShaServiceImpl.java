/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoShaServiceImpl
 * Author:   mac
 * Date:     2021/5/13 3:57 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.service.Impl;

import com.lhn.domain.MiaoshaOrder;
import com.lhn.domain.MiaoshaUser;
import com.lhn.domain.OrderInfo;
import com.lhn.service.GoodsService;
import com.lhn.service.MiaoShaService;
import com.lhn.service.OrderService;
import com.lhn.utils.MD5Util;
import com.lhn.utils.MiaoShaKey;
import com.lhn.utils.UUIDUtil;
import com.lhn.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/13
 * @since 1.0.0
 */
@Service
public class MiaoShaServiceImpl implements MiaoShaService {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.REPEATABLE_READ)
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        boolean success = goodsService.reduceStock(goods);
        if (success){
            //order_info maiosha_order
            return orderService.createOrder(user, goods);
        }
        setGoodsOver(goods.getId());
        return null;
    }

    @Override
    public long getMiaoshaResult(String userId, long goodsId) {
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(Long.parseLong(userId), goodsId);
        if(order != null) {
            //秒杀成功
            return order.getOrderId();
        }else {
            boolean isOver = getGoodsOver(goodsId);
            if(isOver) {
                return -1;
            }else {
                return 0;
            }
        }
    }

    private boolean getGoodsOver(long goodsId) {
        Boolean result = (Boolean) redisTemplate.opsForValue().get(MiaoShaKey.isGoodsOver.getPrefix()+":"+goodsId);
        if (result==null){
            return false;
        }
        return result;
    }

    private void setGoodsOver(Long goodsId) {
        redisTemplate.opsForValue().set(MiaoShaKey.isGoodsOver.getPrefix()+":"+goodsId,true);
    }

    @Override
    public boolean checkPath(MiaoshaUser user, Long goodsId, String path) {
        if(user == null || path == null) {
            return false;
        }
        String pathOld = (String) redisTemplate.opsForValue().get(MiaoShaKey.getMiaoshaPath.getPrefix()+":"+user.getId()+":"+goodsId);
        return path.equals(pathOld);
    }

    @Override
    public String createMiaoshaPath(MiaoshaUser user, long goodsId) {
        if(user == null || goodsId <=0) {
            return null;
        }
        String str = MD5Util.getMD5Code(UUIDUtil.uuid()+"123456");
        redisTemplate.opsForValue().set(MiaoShaKey.getMiaoshaPath.getPrefix()+":"+user.getId()+":"+goodsId, str);
        return str;
    }

    @Override
    public boolean checkVerifyCode(MiaoshaUser user, long goodsId, int verifyCode) {
        if(user == null || goodsId <=0) {
            return false;
        }
        Integer codeOld = (Integer) redisTemplate.opsForValue().get(MiaoShaKey.getMiaoshaVerifyCode.getPrefix()+":"+user.getId()+":"+goodsId);
        if(codeOld == null || codeOld - verifyCode != 0 ) {
            return false;
        }
        redisTemplate.delete(MiaoShaKey.getMiaoshaVerifyCode.getPrefix()+":"+user.getId()+":"+goodsId);
        return true;
    }

    @Override
    public BufferedImage createVerifyCode(MiaoshaUser user, long goodsId) {
        if(user == null || goodsId <=0) {
            return null;
        }
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode, 8, 24);
        g.dispose();
        //把验证码存到redis中
        int rnd = calc(verifyCode);
        redisTemplate.opsForValue().set(MiaoShaKey.getMiaoshaVerifyCode.getPrefix()+":"+user.getId()+":"+goodsId, rnd);
        //输出图片
        return image;
    }

    private static int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (Integer)engine.eval(exp);
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static char[] ops = new char[] {'+', '-', '*'};
    /**
     * + - *
     * */
    private String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp = ""+ num1 + op1 + num2 + op2 + num3;
        return exp;
    }
}

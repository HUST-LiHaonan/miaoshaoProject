/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MQReceiver
 * Author:   mac
 * Date:     2021/5/26 10:48 上午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.lhn.config.MQConfig;
import com.lhn.domain.MiaoshaOrder;
import com.lhn.domain.MiaoshaUser;
import com.lhn.service.GoodsService;
import com.lhn.service.MiaoShaService;
import com.lhn.service.OrderService;
import com.lhn.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/26
 * @since 1.0.0
 */
@Service
@Slf4j
public class MQReceiver {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoShaService miaoShaService;

    @RabbitListener(queues=MQConfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        log.info("receive message:"+message);
        //反序列化
        MiaoshaMessage mm  = JSON.parseObject(message,MiaoshaMessage.class);
        MiaoshaUser user = mm.getUser();
        long goodsId = mm.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0) {
            return;
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(Long.parseLong(user.getId()), goodsId);
        if(order != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        miaoShaService.miaosha(user, goods);
    }
}

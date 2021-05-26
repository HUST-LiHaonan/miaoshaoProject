/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoShaController
 * Author:   mac
 * Date:     2021/5/13 3:41 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.controller;

import com.lhn.domain.MiaoshaOrder;
import com.lhn.domain.MiaoshaUser;
import com.lhn.domain.OrderInfo;
import com.lhn.dto.CodeMsg;
import com.lhn.dto.Result;
import com.lhn.service.GoodsService;
import com.lhn.service.MiaoShaService;
import com.lhn.service.OrderService;
import com.lhn.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/13
 * @since 1.0.0
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoShaController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoShaService miaoShaService;

    @RequestMapping(value = "/do_miaosha",method = RequestMethod.POST)
    @ResponseBody
    public Result<OrderInfo> doMiaosha(@RequestParam("goodsId") Long goodsId, MiaoshaUser user, Model model){
        model.addAttribute("user", user);
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        //判断库存
        //10个商品，req1 req2
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(Long.parseLong(user.getId()), goodsId);
        if(order != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoShaService.miaosha(user, goods);
        return Result.success(orderInfo);
    }
}

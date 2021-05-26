/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: OrderController
 * Author:   mac
 * Date:     2021/5/25 3:14 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.controller;

import com.lhn.domain.MiaoshaUser;
import com.lhn.domain.OrderInfo;
import com.lhn.dto.CodeMsg;
import com.lhn.dto.Result;
import com.lhn.service.GoodsService;
import com.lhn.service.OrderService;
import com.lhn.vo.GoodsVo;
import com.lhn.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/25
 * @since 1.0.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/to_detail")
    @ResponseBody
    public Result<OrderDetailVo> info(MiaoshaUser user, @RequestParam("orderId") long orderId) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
    }
}

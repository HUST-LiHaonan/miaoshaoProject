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

import com.lhn.domain.MiaoshaUser;
import com.lhn.domain.OrderInfo;
import com.lhn.service.GoodsService;
import com.lhn.service.MiaoShaService;
import com.lhn.service.OrderService;
import com.lhn.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.REPEATABLE_READ)
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        //order_info maiosha_order
        return orderService.createOrder(user, goods);
    }
}

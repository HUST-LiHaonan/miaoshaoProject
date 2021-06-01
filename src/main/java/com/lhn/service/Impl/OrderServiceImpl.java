/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: OrderServiceImpl
 * Author:   mac
 * Date:     2021/5/13 3:49 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.service.Impl;

import com.lhn.dao.OrderDao;
import com.lhn.domain.MiaoshaOrder;
import com.lhn.domain.MiaoshaUser;
import com.lhn.domain.OrderInfo;
import com.lhn.service.OrderService;
import com.lhn.key.OrderKey;
import com.lhn.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/13
 * @since 1.0.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    @Transactional(readOnly = true)
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
        MiaoshaOrder order = (MiaoshaOrder) redisTemplate.opsForValue().get(OrderKey.getMiaoshaOrderByUidGid.getPrefix()+":"+userId+":"+goodsId);
        if (order==null){
            //order = orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
            redisTemplate.opsForValue().set(OrderKey.getMiaoshaOrderByUidGid.getPrefix()+":"+userId+":"+goodsId,order);
        }
        return order;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.REPEATABLE_READ)
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(Long.parseLong(user.getId()));
        orderDao.insert(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(Long.parseLong(user.getId()));
        orderDao.insertMiaoshaOrder(miaoshaOrder);
        redisTemplate.opsForValue().set(OrderKey.getMiaoshaOrderByUidGid.getPrefix()+":"+user.getId()+":"+goods.getId(),miaoshaOrder);
        return orderInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderInfo getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }
}

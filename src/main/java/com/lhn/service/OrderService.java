/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: OrderService
 * Author:   mac
 * Date:     2021/5/13 3:49 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.service;

import com.lhn.domain.MiaoshaOrder;
import com.lhn.domain.MiaoshaUser;
import com.lhn.domain.OrderInfo;
import com.lhn.vo.GoodsVo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/13
 * @since 1.0.0
 */
public interface OrderService {

    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId);

    OrderInfo createOrder(MiaoshaUser user, GoodsVo goods);

    OrderInfo getOrderById(Long id);
}

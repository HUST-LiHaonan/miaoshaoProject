/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoShaService
 * Author:   mac
 * Date:     2021/5/13 3:57 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.service;

import com.lhn.domain.MiaoshaUser;
import com.lhn.domain.OrderInfo;
import com.lhn.vo.GoodsVo;

import java.awt.image.BufferedImage;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/13
 * @since 1.0.0
 */
public interface MiaoShaService {
    OrderInfo miaosha(MiaoshaUser user, GoodsVo goods);

    long getMiaoshaResult(String id, long goodsId);

    boolean checkPath(MiaoshaUser user, Long goodsId, String path);

    String createMiaoshaPath(MiaoshaUser user, long goodsId);

    boolean checkVerifyCode(MiaoshaUser user, long goodsId, int verifyCode);

    BufferedImage createVerifyCode(MiaoshaUser user, long goodsId);
}

/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: GoodsKey
 * Author:   mac
 * Date:     2021/5/25 9:28 上午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.utils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/25
 * @since 1.0.0
 */
public class GoodsKey extends BasePrefix{

    public GoodsKey(String prefix) {
        super(prefix);
    }

    public GoodsKey(String prefix, Integer expireSeconds) {
        super(prefix, expireSeconds);
    }

    public static GoodsKey getGoodsList  = new GoodsKey("gl",60);
    public static GoodsKey getGoodsDetail  = new GoodsKey("gd",15);

}

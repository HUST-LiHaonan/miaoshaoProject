/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoShaUserKey
 * Author:   mac
 * Date:     2021/5/11 1:30 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.utils;

import com.lhn.domain.MiaoshaUser;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/11
 * @since 1.0.0
 */
public class MiaoShaUserKey extends BasePrefix{

    public static final int TOKEN_EXPIRE = 3600*24 * 2;
    private MiaoShaUserKey(int expireSeconds, String prefix) {
        super(prefix, expireSeconds);
    }
    public static MiaoShaUserKey token = new MiaoShaUserKey(TOKEN_EXPIRE, "tk");

    public MiaoShaUserKey(String prefix, Integer expireSeconds) {
        super(prefix, expireSeconds);
    }

    public MiaoShaUserKey(String prefix) {
        super(prefix);
    }
}

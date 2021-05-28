/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: AccessKey
 * Author:   mac
 * Date:     2021/5/27 2:07 下午
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
 * @create 2021/5/27
 * @since 1.0.0
 */
public class AccessKey extends BasePrefix{
    private AccessKey( int expireSeconds, String prefix) {
        super(prefix, expireSeconds);
    }

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }
}

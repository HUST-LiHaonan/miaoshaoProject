/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: UserKey
 * Author:   mac
 * Date:     2021/5/11 1:59 下午
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
 * @create 2021/5/11
 * @since 1.0.0
 */
public class UserKey extends BasePrefix{

    public UserKey(String prefix, Integer expireSeconds) {
        super(prefix, expireSeconds);
    }

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}

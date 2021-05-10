/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: UserKey
 * Author:   mac
 * Date:     2021/5/10 2:17 下午
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
 * @create 2021/5/10
 * @since 1.0.0
 */
public class UserKey extends BasePrefix{

    public UserKey(String prefix, Integer expireSeconds) {
        super(prefix, expireSeconds);
    }

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById  = new UserKey("id");
    public static UserKey getByName = new UserKey("name");

    public static String getUserKeyById(MiaoshaUser user){
        return getById.getPrefix()+user.getId();
    }

    public static String getUserByName(MiaoshaUser user){
        return getByName.getPrefix()+user.getNickname();
    }
}

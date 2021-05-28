/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MD5Util
 * Author:   mac
 * Date:     2021/5/10 8:07 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/10
 * @since 1.0.0
 */
public class MD5Util {
    private static final String SALT = "1a2b3c4d5f";
    public static String getMD5Code(String str){
        return DigestUtils.md5Hex(str);
    }

    public static String inputPassWordToFormPassWord(String inputPassWord){
        String mask = inputPassWord+SALT.charAt(1)+SALT.charAt(3)+SALT.charAt(5)+SALT.charAt(7);
        return getMD5Code(mask);
    }

    public static String formPassWordToDbPassWord(String formPassWord, String salt){
        String mask = formPassWord+salt.charAt(1)+salt.charAt(3)+salt.charAt(5)+salt.charAt(7);
        return getMD5Code(mask);
    }
}

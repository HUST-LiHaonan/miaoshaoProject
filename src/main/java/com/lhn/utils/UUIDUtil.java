/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: UUIDUtil
 * Author:   mac
 * Date:     2021/5/11 1:05 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.utils;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/11
 * @since 1.0.0
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

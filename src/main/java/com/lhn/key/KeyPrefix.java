/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: BaseKey
 * Author:   mac
 * Date:     2021/5/10 1:58 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.key;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/10
 * @since 1.0.0
 */
public interface KeyPrefix {
    Integer expireSeconds();
    String getPrefix();
}

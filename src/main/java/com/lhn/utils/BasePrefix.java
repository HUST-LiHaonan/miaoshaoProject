/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: BasePrefix
 * Author:   mac
 * Date:     2021/5/10 2:00 下午
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
 * @create 2021/5/10
 * @since 1.0.0
 */
public abstract class BasePrefix implements KeyPrefix{
    private String prefix;
    private Integer expireSeconds;

    public BasePrefix(String prefix, Integer expireSeconds) {
        this.prefix = prefix;
        this.expireSeconds = expireSeconds;
    }

    public BasePrefix(String prefix) {
        this(prefix,0);
    }

    @Override
    public Integer expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":"+prefix;
    }
}

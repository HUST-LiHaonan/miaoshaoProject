/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: Result
 * Author:   mac
 * Date:     2021/5/10 2:06 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/10
 * @since 1.0.0
 */
@Data
@Component
public class Result<T> {
    private int code;
    private String msg;
    private T data;
}

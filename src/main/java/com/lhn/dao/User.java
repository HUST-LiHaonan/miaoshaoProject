/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: User
 * Author:   mac
 * Date:     2021/5/10 1:16 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.dao;

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
public class User {
    private Integer id;
    private String username;
    private Integer age;
}

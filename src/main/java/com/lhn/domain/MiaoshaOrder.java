/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoshaOrder
 * Author:   mac
 * Date:     2021/5/12 9:53 上午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/12
 * @since 1.0.0
 */
@Data
@Component
public class MiaoshaOrder {
    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;
}

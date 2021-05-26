/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: OrderDetailVo
 * Author:   mac
 * Date:     2021/5/25 2:57 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.vo;

import com.lhn.domain.OrderInfo;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/25
 * @since 1.0.0
 */
@Component
@Data
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;
}

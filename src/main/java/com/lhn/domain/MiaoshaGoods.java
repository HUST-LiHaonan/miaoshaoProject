/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoshaGoods
 * Author:   mac
 * Date:     2021/5/12 9:52 上午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

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
public class MiaoshaGoods {
    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private Double miaoshaPrice;
}

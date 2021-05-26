/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: GoodsDetailVo
 * Author:   mac
 * Date:     2021/5/25 2:56 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.vo;

import com.lhn.domain.MiaoshaUser;
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
public class GoodsDetailVo {
    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private GoodsVo goods ;
    private MiaoshaUser user;
}

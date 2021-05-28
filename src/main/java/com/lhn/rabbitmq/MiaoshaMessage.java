/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoshaMessage
 * Author:   mac
 * Date:     2021/5/27 10:58 上午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.rabbitmq;

import com.lhn.domain.MiaoshaUser;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/27
 * @since 1.0.0
 */
@Component
@Data
public class MiaoshaMessage implements Serializable {
    private MiaoshaUser user;
    private long goodsId;
}

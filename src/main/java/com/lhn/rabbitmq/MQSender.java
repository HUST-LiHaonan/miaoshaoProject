/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MQSender
 * Author:   mac
 * Date:     2021/5/26 10:47 上午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.lhn.config.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/26
 * @since 1.0.0
 */
@Service
@Slf4j
public class MQSender {
    @Autowired
    private AmqpTemplate template;

    public void sendMiaoshaMessage(MiaoshaMessage mm) {
        String msg = JSON.toJSONString(mm);
        log.info("send message:"+msg);
        template.convertAndSend(MQConfig.MIAOSHA_QUEUE, msg);
    }
}

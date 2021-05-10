/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: TransactionConfig
 * Author:   mac
 * Date:     2021/5/10 11:00 上午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/10
 * @since 1.0.0
 */
@Configuration
public class TransactionConfig {
    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}

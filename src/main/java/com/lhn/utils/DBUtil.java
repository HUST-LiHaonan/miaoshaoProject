/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: DBUtil
 * Author:   mac
 * Date:     2021/5/25 1:12 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/25
 * @since 1.0.0
 */
public class DBUtil {

    public static Connection getConn() throws Exception{
        String url = "jdbc:mysql://lhnwithcs.love:3306/miaosha?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "123456";
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        return DriverManager.getConnection(url,username, password);
    }
}

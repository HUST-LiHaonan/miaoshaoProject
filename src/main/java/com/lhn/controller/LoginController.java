/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: LoginController
 * Author:   mac
 * Date:     2021/5/10 8:24 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.controller;

import com.lhn.dao.MiaoShaUserDao;
import com.lhn.domain.MiaoshaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/10
 * @since 1.0.0
 */
@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private MiaoShaUserDao miaoShaUserDao;

    @RequestMapping("to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("test")
    @ResponseBody
    public MiaoshaUser test(){
        return miaoShaUserDao.getUserById(18371935971L);
    }
}

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

import com.lhn.domain.MiaoshaUser;
import com.lhn.dto.CodeMsg;
import com.lhn.dto.Result;
import com.lhn.service.MiaoShaUserService;
import com.lhn.utils.MD5Util;
import com.lhn.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/10
 * @since 1.0.0
 */
@Controller
@RequestMapping("/miaosha")
@Slf4j
public class LoginController {
    @Autowired
    private MiaoShaUserService userService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(@Valid LoginVo loginVo){
        log.info(loginVo.toString());
        MiaoshaUser user = userService.getUserById(loginVo.getMobile());
        String formPassWord = loginVo.getPassword();
        String dbPassWord = user.getPassword();
        if (MD5Util.formPassWordToDbPassWord(formPassWord,user.getSalt()).equals(dbPassWord)) {
            System.out.println(user);
            System.out.println("密码正确！");
            return Result.success(true);
        }else {
            Result<Boolean> result = Result.error(CodeMsg.PASSWORD_ERROR);
            result.setData(false);
            return result;
        }
    }

}

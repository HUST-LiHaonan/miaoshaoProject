/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: GoodsController
 * Author:   mac
 * Date:     2021/5/11 1:44 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.controller;

import com.lhn.domain.MiaoshaUser;
import com.lhn.service.Impl.MiaoShaUserServiceImpl;
import com.lhn.service.MiaoShaUserService;
import com.lhn.utils.MiaoShaUserKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/11
 * @since 1.0.0
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    @Autowired
    MiaoShaUserService userService;
    @RequestMapping("/to_list")
    public String list(Model model, MiaoshaUser user){
        model.addAttribute("user",user);
        return "goods_list";
    }
}

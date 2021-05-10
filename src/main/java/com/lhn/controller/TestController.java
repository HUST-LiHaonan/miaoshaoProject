/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: TestController
 * Author:   mac
 * Date:     2021/5/10 10:11 上午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/10
 * @since 1.0.0
 */
@Controller
@RequestMapping("/hello")
public class TestController {
    @RequestMapping("/thymeleaf")
    public String test(Model model){
        model.addAttribute("name","lhn");
        return "hello";
    }
}

/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoShaUserService
 * Author:   li
 * Date:     2021/5/11 上午9:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.service;

import com.lhn.domain.MiaoshaUser;
import com.lhn.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author li
 * @create 2021/5/11
 * @since 1.0.0
 */
public interface MiaoShaUserService {
    MiaoshaUser getUserById(String id);

    Boolean updatePasswordById(String token,String id,String password);

    Boolean login(HttpServletResponse response, LoginVo loginVo);

    MiaoshaUser getUserByToken(String token,HttpServletResponse response);


}

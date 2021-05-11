/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoShaUserServiceImpl
 * Author:   li
 * Date:     2021/5/11 上午9:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.service.Impl;

import com.lhn.dao.MiaoShaUserDao;
import com.lhn.domain.MiaoshaUser;
import com.lhn.dto.CodeMsg;
import com.lhn.exception.GlobalException;
import com.lhn.service.MiaoShaUserService;
import com.lhn.utils.MD5Util;
import com.lhn.utils.MiaoShaUserKey;
import com.lhn.utils.UUIDUtil;
import com.lhn.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author li
 * @create 2021/5/11
 * @since 1.0.0
 */
@Service
public class MiaoShaUserServiceImpl implements MiaoShaUserService {

    public static final String COOKI_NAME_TOKEN = "token";


    @Autowired
    private MiaoShaUserDao userDao;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public MiaoshaUser getUserById(String id) {
        MiaoshaUser user = userDao.getUserById(id);
        return user;
    }

    @Override
    public boolean login(HttpServletResponse response, LoginVo loginVo){
        if(loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getUserById(mobile);
        if(Objects.isNull(user)) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassWordToDbPassWord(formPass, saltDB);
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        addCookie(user,response);
        return true;
    }

    //设置token
    private void addCookie(MiaoshaUser user,HttpServletResponse response){
        String token = UUIDUtil.uuid();
        //设置token
        redisTemplate.opsForValue().set(MiaoShaUserKey.token.getPrefix()+":"+token,user,MiaoShaUserKey.token.expireSeconds(), TimeUnit.SECONDS);
        //设置cookie
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN,MiaoShaUserKey.token.getPrefix()+":"+token);
        cookie.setMaxAge(MiaoShaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public MiaoshaUser getUserByToken(String token,HttpServletResponse response) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        Object user = redisTemplate.opsForValue().get(token);
        if (!(user instanceof MiaoshaUser)){
            return null;
        }
        addCookie((MiaoshaUser) user,response);
        return (MiaoshaUser) user;
    }
}

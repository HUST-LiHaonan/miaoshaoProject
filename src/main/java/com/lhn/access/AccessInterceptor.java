/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: AccessInterceptor
 * Author:   mac
 * Date:     2021/5/27 2:01 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.access;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.lhn.domain.MiaoshaUser;
import com.lhn.dto.CodeMsg;
import com.lhn.dto.Result;
import com.lhn.service.Impl.MiaoShaUserServiceImpl;
import com.lhn.service.MiaoShaUserService;
import com.lhn.key.AccessKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/27
 * @since 1.0.0
 */
public class AccessInterceptor implements HandlerInterceptor {

    @Autowired
    private MiaoShaUserService userService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(handler instanceof HandlerMethod) {
            MiaoshaUser user = getUser(request, response);
            UserContext.setUser(user);
            HandlerMethod hm = (HandlerMethod)handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null) {
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = request.getRequestURI();
            if(needLogin) {
                if(user == null) {
                    render(response, CodeMsg.SESSION_ERROR);
                    return false;
                }
                key += ":" + user.getId();
            }
            AccessKey ak = AccessKey.withExpire(seconds);
            Integer count = (Integer) redisTemplate.opsForValue().get(ak.getPrefix()+key);
            if(count  == null) {
                redisTemplate.opsForValue().set(ak.getPrefix()+key,1,ak.expireSeconds(),TimeUnit.SECONDS);
            }else if(count < maxCount) {
                redisTemplate.opsForValue().increment(ak.getPrefix()+key);
            }else {
                render(response, CodeMsg.ACCESS_LIMIT_REACHED);
                return false;
            }
        }
        return true;
    }

    private void render(HttpServletResponse response, CodeMsg cm)throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str  = JSON.toJSONString(Result.error(cm));
        out.write(str.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

    private MiaoshaUser getUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter(MiaoShaUserServiceImpl.COOKI_NAME_TOKEN);
        String cookieToken = getCookieValue(request);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        return userService.getUserByToken(token,response);
    }

    private String getCookieValue(HttpServletRequest request) {
        Cookie[]  cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(MiaoShaUserServiceImpl.COOKI_NAME_TOKEN)) {
                return cookie.getValue();
            }
        }
        return null;
    }

}

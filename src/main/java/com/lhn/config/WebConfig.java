/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: WebConfig
 * Author:   mac
 * Date:     2021/5/11 3:06 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.config;

import com.lhn.domain.MiaoshaUser;
import com.lhn.service.Impl.MiaoShaUserServiceImpl;
import com.lhn.service.MiaoShaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/11
 * @since 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private UserArgumentResolver resolver;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(resolver);
    }

    @Component
    static class UserArgumentResolver implements HandlerMethodArgumentResolver{
        @Autowired
        MiaoShaUserService userService;
        @Override
        public boolean supportsParameter(MethodParameter methodParameter) {
            if (methodParameter.getParameterType()== MiaoshaUser.class){
                return true;
            }else {
                return false;
            }
        }

        @Override
        public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
            HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
            HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
            String paramToken = request.getParameter(MiaoShaUserServiceImpl.COOKI_NAME_TOKEN);
            String cookieToken = getCookieValue(request,MiaoShaUserServiceImpl.COOKI_NAME_TOKEN);
            String token = StringUtils.isEmpty(paramToken) ? cookieToken:paramToken;
            MiaoshaUser user = userService.getUserByToken(token,response);
            return user;
        }

        private String getCookieValue(HttpServletRequest request,String cookieName){
            Cookie[] cookies =request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
            return "";
        }
    }
}



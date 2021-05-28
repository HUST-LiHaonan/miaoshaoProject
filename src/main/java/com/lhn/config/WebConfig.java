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

import com.lhn.access.AccessInterceptor;
import com.lhn.access.UserContext;
import com.lhn.domain.MiaoshaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
        @Override
        public boolean supportsParameter(MethodParameter methodParameter) {
            if (methodParameter.getParameterType()== MiaoshaUser.class){
                return true;
            }else {
                return false;
            }
        }
        @Override
        public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
            return UserContext.getUser();
        }

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String [] exculudes = new String[]{"/*.html","/html/**","/js/**","/css/**","/images/**"};
        registry.addInterceptor(accessInterceptor()).addPathPatterns("/**").excludePathPatterns(exculudes);
    }

    @Bean
    public AccessInterceptor accessInterceptor(){
        return new AccessInterceptor();
    }
}



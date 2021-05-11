/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: GlobleExceptionHandler
 * Author:   li
 * Date:     2021/5/11 上午10:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.exception;

import com.lhn.dto.CodeMsg;
import com.lhn.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author li
 * @create 2021/5/11
 * @since 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GlobalException.class)
    public Result<String> globalExceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        GlobalException ex = (GlobalException)e;
        return Result.error(ex.getCm());
    }
    @ExceptionHandler(value = BindException.class)
    public Result<String> bindExceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        BindException ex = (BindException)e;
        List<ObjectError> errors = ex.getAllErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();
        return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
    }

    @ExceptionHandler(value=Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error(CodeMsg.SERVER_ERROR);
    }


}

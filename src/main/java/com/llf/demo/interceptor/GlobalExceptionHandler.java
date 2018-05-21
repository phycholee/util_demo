package com.llf.demo.interceptor;

import com.llf.demo.common.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Oliver.li
 * @Description: 全局异常处理
 * @date: 2018/5/21 15:19
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonData handleException(Exception e){
        logger.error(e.getMessage(), e);
        return JsonData.fail(e.getMessage(), null);
    }
}

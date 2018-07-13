package com.llf.demo.interceptor;

import com.llf.demo.common.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

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

        //Hibernate Validator验证消息返回
        BindingResult result = null;
        if (e instanceof MethodArgumentNotValidException){
            result = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException){
            result = ((BindException) e).getBindingResult();
        } else if (e instanceof ConstraintViolationException){
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
            StringBuilder errorMsg = new StringBuilder();
            for (ConstraintViolation<?> violation : constraintViolations) {
                errorMsg.append(violation.getMessage()).append(",");
            }
            errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
            return JsonData.fail(errorMsg.toString());
        }
        if (result != null) {
            StringBuilder errorMsg = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                errorMsg.append(error.getDefaultMessage()).append(",");
            }
            errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
            return JsonData.fail(errorMsg.toString());
        }

        return JsonData.error(e.getMessage(), null);
    }
}

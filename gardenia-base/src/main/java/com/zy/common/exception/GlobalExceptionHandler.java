package com.zy.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Gardenia
 * @Description TODO
 * @Date 2024/4/24 0:01
 * @Version 1.0
 */

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ArithmeticException.class)
    public String error(ArithmeticException e) {
        return "error:" + e.getMessage();
    }

}

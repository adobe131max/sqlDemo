package com.example.exception;

import com.example.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result err(Exception exception) {
        exception.printStackTrace();
        return Result.error("operation failed");
    }
}

package com.example.server.exception;

import com.example.server.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class PoiExceptionHandler {

    @ExceptionHandler(PoiException.class)
    public Result poiExceptionHandler(Exception e) {
        log.info("poiExceptionHandler:{}",e);
        return Result.fail();
    }

    @ExceptionHandler(Exception.class)
    public Result serviceException(Exception e) {
        return Result.fail();
    }
}

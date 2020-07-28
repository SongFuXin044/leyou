package com.leyou.common.advice;

import com.leyou.common.exceptions.lyException;
import com.leyou.common.pojo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Description:
 * @Author Administrator
 * @Date 2020/7/26 13:55
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(lyException.class)
    public ResponseEntity<ExceptionResult> handlerException(lyException e){
         return ResponseEntity.status(e.getEnumsException().getCode()).body(new ExceptionResult(e.getEnumsException()));
    }

}

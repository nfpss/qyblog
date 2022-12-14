package com.qy.exception;

import com.qy.response.AppHttpCodeEnum;
import com.qy.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 18:30
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public ResponseResult<Void> bizExcepetion(BizException bizException) {
        log.error("业务异常:{}", bizException);
        return ResponseResult.error(bizException.getCode(), bizException.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> exceptionHandler(Exception e) {
        //打印异常信息
        log.error("其余异常 {}", e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.error(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}

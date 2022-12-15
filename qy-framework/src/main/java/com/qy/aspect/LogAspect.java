package com.qy.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/15 18:13
 **/
@Aspect
@Slf4j
@Component
public class LogAspect {

    @Pointcut("@annotation(com.qy.annotion.LogPrint)")
    public void poincut() {
    }

    @Around("poincut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("=======Start=======");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 打印请求 URL
        log.info("URL            : {}", request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}", "不晓得");
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", methodSignature.getDeclaringTypeName(), methodSignature.getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", Arrays.toString(proceedingJoinPoint.getArgs()));
        Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        // 打印出参
        log.info("Response       : {}", proceed);
        // 结束后换行
        log.info("=======End=======" + System.lineSeparator());
        return proceed;
    }
}

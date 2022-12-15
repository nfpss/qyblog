package com.qy.annotion;

import java.lang.annotation.*;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/15 18:14
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogPrint {
}

package com.qy.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: qy
 * @create: 2022/12/13 0:10
 * @description:
 **/
public class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    public static <T> T copyBean(Object source, Class<T> targetClass) {
        T t = null;
        try {
            t = targetClass.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    public static <T, R> List<R> copyList(List<T> sourceList, Class<R> targetClass) {
        return sourceList.stream().map(t -> BeanCopyUtils.copyBean(t, targetClass)).collect(Collectors.toList());
    }
}

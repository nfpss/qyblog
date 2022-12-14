package com.qy.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qy.response.AppHttpCodeEnum;
import com.qy.exception.BizException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Set;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 14:59
 **/
public class ValidatorUtils {

    private ValidatorUtils() {
    }


    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();


    public static void valid(Object data) {
        if (Objects.isNull(data)) {
            throw new BizException(AppHttpCodeEnum.PARAM_ERROR);
        }
        Set<ConstraintViolation<Object>> validate = VALIDATOR.validate(data);
        if (CollectionUtils.isEmpty(validate)) {
            return;
        }
        for (ConstraintViolation<Object> objectConstraintViolation : validate) {
            if (StringUtils.isNotBlank(objectConstraintViolation.getMessage())) {
                throw new BizException(AppHttpCodeEnum.PARAM_ERROR);
            }
        }
    }
}

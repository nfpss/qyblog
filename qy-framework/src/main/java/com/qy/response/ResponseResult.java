package com.qy.response;

import com.qy.exception.BizException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 14:31
 **/
@Data
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 837736828204874239L;

    private Integer code;

    private String msg;

    private T data;

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static <T> ResponseResult<T> success(AppHttpCodeEnum appHttpCodeEnum, T data) {
        return new ResponseResult<>(appHttpCodeEnum.getCode(), appHttpCodeEnum.getMsg(), data);
    }

    public static <T> ResponseResult<T> error(Integer code, String msg) {
        return new ResponseResult<>(code, msg, null);
    }

    public static <T> ResponseResult<T> error(AppHttpCodeEnum appHttpCodeEnum, String msg) {
        return error(appHttpCodeEnum.getCode(), msg);
    }

    public static <T> ResponseResult<T> error(AppHttpCodeEnum appHttpCodeEnum) {
        return error(appHttpCodeEnum.getCode(), appHttpCodeEnum.getMsg());
    }

    public static <T> ResponseResult<T> error(BizException bizException) {
        return error(bizException.getCode(), bizException.getMsg());
    }

    public static <T> ResponseResult<T> systemError() {
        return error(AppHttpCodeEnum.SYSTEM_ERROR);
    }
}

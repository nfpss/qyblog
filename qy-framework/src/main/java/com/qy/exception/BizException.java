package com.qy.exception;

import com.qy.response.AppHttpCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 14:47
 **/

@Data
public class BizException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 4482361089548961406L;
    private Integer code;

    private String msg;

    public BizException(Integer code, String msg) {
        super(code + ":" + msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(AppHttpCodeEnum appHttpCodeEnum) {
        super(appHttpCodeEnum.getCode() + ":" + appHttpCodeEnum.getMsg());
        this.code = appHttpCodeEnum.getCode();
        this.msg = appHttpCodeEnum.getMsg();
    }

    public BizException(AppHttpCodeEnum appHttpCodeEnum, String msg) {
        super(appHttpCodeEnum.getCode() + ":" + msg);
        this.code = appHttpCodeEnum.getCode();
        this.msg = msg;
    }
}

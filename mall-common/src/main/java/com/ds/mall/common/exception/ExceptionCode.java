package com.ds.mall.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tb
 * @date 2019/1/7 14:01
 */
@AllArgsConstructor
@Getter
public enum ExceptionCode {
    //客户端无效
    CLIENT_INVALID(40301),
    //客户端被禁
    CLIENT_FORBIDDEN(40302),
    //用户无效
    USER_INVALID(40303),
    //密码无效
    PASSWORD_INVALID(40304),
    //业务错误
    BIZ_ERROR(40305),
    //系统错误
    SYSTEM_ERROR(40399),
    //其他
    OTHER_ERROR(40300)
    ;
    private int code;
}

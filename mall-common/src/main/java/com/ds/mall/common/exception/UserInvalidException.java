package com.ds.mall.common.exception;

/**
 * @author tb
 * @date 2019/1/7 14:06
 */
public class UserInvalidException extends AbstractMallException {

    public UserInvalidException(String message) {
        super(message, ExceptionCode.USER_INVALID);
    }
}

package com.ds.mall.common.exception;

/**
 * @author tb
 * @date 2019/1/7 14:06
 */
public class PasswordInvalidException extends AbstractMallException {

    public PasswordInvalidException(String message) {
        super(message, ExceptionCode.PASSWORD_INVALID);
    }
}

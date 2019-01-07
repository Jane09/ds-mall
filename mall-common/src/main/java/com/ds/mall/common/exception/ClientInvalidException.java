package com.ds.mall.common.exception;

/**
 * @author tb
 * @date 2019/1/7 14:08
 */
public class ClientInvalidException extends AbstractMallException {

    public ClientInvalidException(String message) {
        super(message, ExceptionCode.CLIENT_INVALID);
    }
}

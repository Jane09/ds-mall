package com.ds.mall.common.exception;

/**
 * @author tb
 * @date 2019/1/7 14:06
 */
public class ClientForbiddenException extends AbstractMallException {

    public ClientForbiddenException(String message) {
        super(message, ExceptionCode.CLIENT_FORBIDDEN);
    }
}

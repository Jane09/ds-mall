package com.ds.mall.common.exception;

/**
 * @author tb
 * @date 2019/1/7 14:06
 */
public class MallSystemException extends AbstractMallException {

    public MallSystemException(String message) {
        super(message, ExceptionCode.SYSTEM_ERROR);
    }
}

package com.ds.mall.common.exception;

/**
 * @author tb
 * @date 2019/1/7 14:06
 */
public class MallBizException extends AbstractMallException {

    public MallBizException(String message) {
        super(message, ExceptionCode.BIZ_ERROR);
    }
}

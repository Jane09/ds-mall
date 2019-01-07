package com.ds.mall.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tb
 * @date 2019/1/7 13:59
 */
@Getter
@Setter
@NoArgsConstructor
public class AbstractMallException extends RuntimeException {

    private int status;

    public AbstractMallException(String message,ExceptionCode code) {
        super(message);
        this.status = code.getCode();
    }

    public AbstractMallException(String message) {
        super(message);
    }

    public AbstractMallException(String message, Throwable cause) {
        super(message, cause);
    }
}

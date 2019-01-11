package com.ds.mall.uid.exception;

import com.ds.mall.common.exception.AbstractMallException;

/**
 * @author tb
 * @date 2019/1/11 10:14
 */
public class MallZookeeperException extends AbstractMallException {

    public MallZookeeperException(String message) {
        super(message);
    }

    public MallZookeeperException(String message, Throwable cause) {
        super(message, cause);
    }
}

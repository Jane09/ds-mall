package com.ds.mall.uid;

/**
 * @author tb
 * @date 2019/1/10 9:47
 */
public interface Module {

    void start();

    void destroy();

    boolean isStarted();
}

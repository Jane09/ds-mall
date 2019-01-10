package com.ds.mall.uid;

import com.ds.mall.uid.config.UidConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tb
 * @date 2019/1/10 9:48
 */
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractModule implements Module {

    protected volatile boolean started = false;
    protected volatile boolean retry = true;

    protected final UidConfig uidConfig;

    @Override
    public void start() {
        try{
            doStart();
            started =true;
        }catch (Exception ex) {
            log.error("启动失败",ex);
            destroy();
        }
    }

    @Override
    public void destroy() {
        started =false;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    protected abstract void doStart() throws Exception;
}

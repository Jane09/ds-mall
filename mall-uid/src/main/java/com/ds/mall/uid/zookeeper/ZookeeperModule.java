package com.ds.mall.uid.zookeeper;

import com.ds.mall.uid.AbstractModule;
import com.ds.mall.uid.config.UidConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tb
 * @date 2019/1/9 17:25
 */
@Slf4j
public class ZookeeperModule extends AbstractModule {

    public ZookeeperModule(UidConfig uidConfig) {
        super(uidConfig);
    }

    @Override
    protected void doStart() throws Exception {
        log.info("启动zookeeper");
    }
}

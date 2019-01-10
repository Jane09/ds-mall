package com.ds.mall.uid.runner;

import com.ds.mall.uid.config.UidConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @author tb
 * @date 2019/1/10 9:53
 */
@Slf4j
@Configuration
public class ModuleRunner implements CommandLineRunner {

    private final UidConfig uidConfig;

    @Autowired
    public ModuleRunner(UidConfig uidConfig) {
        this.uidConfig = uidConfig;
    }

    @Override
    public void run(String... args) throws Exception {
        //启动module
//        new ZookeeperModule(uidConfig).start();
//        new ThriftModule(uidConfig).service(thriftService).start();
    }
}

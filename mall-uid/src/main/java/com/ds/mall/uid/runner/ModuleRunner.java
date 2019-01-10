package com.ds.mall.uid.runner;

import com.ds.mall.uid.config.UidConfig;
import com.ds.mall.uid.thrift.ThriftModule;
import com.ds.mall.uid.zookeeper.ZookeeperModule;
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

    @Autowired
    private UidConfig uidConfig;

    @Override
    public void run(String... args) throws Exception {
        //启动module
        ZookeeperModule zookeeper = new ZookeeperModule(uidConfig);
        zookeeper.start();
        ThriftModule thrift = new ThriftModule(uidConfig);
        thrift.start();
    }
}
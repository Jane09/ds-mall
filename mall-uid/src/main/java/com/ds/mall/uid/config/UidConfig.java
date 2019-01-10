package com.ds.mall.uid.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tb
 * @date 2019/1/9 17:29
 */
@Component
@ConfigurationProperties(prefix = "uid")
@Getter
@Setter
public class UidConfig {

    private Integer datacenter;
    private int port;
    private int minWorkerThreads = 1;
    private int maxWorkerThreads = 1;
    private ZookeeperConfig zookeeper;
}

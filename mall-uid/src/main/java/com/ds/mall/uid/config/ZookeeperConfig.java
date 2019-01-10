package com.ds.mall.uid.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author tb
 * @date 2019/1/10 10:06
 */
@Getter
@Setter
public class ZookeeperConfig {

    private int port;
    private List<String> servers;
    private String persistent;
    private String ephemeral;
    private int connectionTimeoutMs;
    private int sessionTimeoutMs;
    private int retryTimes;
    private int retryIntervalMs;
    private int retryIntervalceilingMs;
    private int heartbeatIntervalMs;
    private int averageTimestampThreshold;
}

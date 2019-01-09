package com.ds.mall.uid.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tb
 * @date 2019/1/9 17:29
 */
@Component
@ConfigurationProperties(prefix = "uid")
public class UidConfig {

}

package com.ds.mall.backend;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tb
 * @date 2019/1/8 14:03
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BackendApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BackendApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}

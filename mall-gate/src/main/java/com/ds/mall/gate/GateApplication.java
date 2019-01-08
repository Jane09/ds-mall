package com.ds.mall.gate;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tb
 * @date 2019/1/8 10:24
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GateApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GateApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}

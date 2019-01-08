package com.ds.mall.trace;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tb
 * @date 2019/1/8 10:27
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TraceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TraceApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}

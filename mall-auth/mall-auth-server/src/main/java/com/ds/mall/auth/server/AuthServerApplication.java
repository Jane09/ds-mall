package com.ds.mall.auth.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tb
 * @date 2019/1/7 19:02
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthServerApplication  {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthServerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}

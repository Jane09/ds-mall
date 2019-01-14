package com.ds.mall.gate;

import com.ds.mall.auth.client.EnableMallAuthClient;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tb
 * @date 2019/1/8 10:24
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableMallAuthClient
@EnableFeignClients({"com.ds.mall.auth.client.feign","com.ds.mall.gate.feign"})
public class GateApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GateApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}

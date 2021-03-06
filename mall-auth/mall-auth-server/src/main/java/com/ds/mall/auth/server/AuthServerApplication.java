package com.ds.mall.auth.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  客户认证
 *  服务鉴权
 *
 *  client m - n service
 *
 * @author tb
 * @date 2019/1/7 19:02
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.ds.mall.auth.server.mapper")
public class AuthServerApplication  {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthServerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}

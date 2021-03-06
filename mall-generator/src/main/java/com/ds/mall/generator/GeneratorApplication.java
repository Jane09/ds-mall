package com.ds.mall.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tb
 * @date 2019/1/7 14:27
 */
@SpringBootApplication
@MapperScan("com.ds.mall.generator.mapper")
@EnableDiscoveryClient
public class GeneratorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GeneratorApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}

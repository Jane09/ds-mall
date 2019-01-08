package com.ds.mall.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author tb
 * @date 2019/1/4 15:43
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
@Configuration
public class MallMonitorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallMonitorApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}

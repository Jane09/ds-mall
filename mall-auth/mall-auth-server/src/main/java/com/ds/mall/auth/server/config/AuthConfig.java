package com.ds.mall.auth.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tb
 * @date 2019/1/11 14:08
 */
@Component
@ConfigurationProperties(prefix = "mall.auth")
@Getter
@Setter
public class AuthConfig {

    //请求头里面添加的header参数名称
    private String tokenHeader;
    //程序集群内部之间的访问配置
    private AuthServicesConfig services;
    //外部访问的程序控制
    private AuthClientsConfig clients;
    //JWT配置相关
    private JwtConfig jwt;
}

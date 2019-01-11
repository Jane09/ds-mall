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

    private AuthServicesConfig server;

    private AuthClientsConfig client;

    private JwtConfig jwt;
}

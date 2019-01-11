package com.ds.mall.auth.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tb
 * @date 2019/1/11 14:08
 */
@Component
@ConfigurationProperties(prefix = "mall.auth")
public class AuthConfig {

    private AuthServicesConfig server;

    private AuthClientConfig client;

    private JwtConfig jwt;
}

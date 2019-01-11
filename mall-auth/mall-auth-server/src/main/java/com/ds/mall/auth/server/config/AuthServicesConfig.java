package com.ds.mall.auth.server.config;

import lombok.Getter;
import lombok.Setter;
/**
 * @author tb
 * @date 2019/1/11 14:06
 */
@Getter
@Setter
public class AuthServicesConfig {
    private String id;
    private String secret;
    private String tokenHeader;
    private int expire;
    private String rsa;
}

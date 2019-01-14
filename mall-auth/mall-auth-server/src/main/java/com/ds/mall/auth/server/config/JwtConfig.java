package com.ds.mall.auth.server.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tb
 * @date 2019/1/11 14:08
 */
@Getter
@Setter
public class JwtConfig {
    //token超时时间
    private int expire;
    //密钥
    private String secret;
}

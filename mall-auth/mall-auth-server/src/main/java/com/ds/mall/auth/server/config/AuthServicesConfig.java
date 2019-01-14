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
    //服务注册到consul的名称
    private String code;
    //密钥
    private String secret;
    //超时
    private int expire;
    //RSA密码
    private String password;
    //初始化之后生成的密钥对
    private byte[] pubKey;
    private byte[] priKey;
}

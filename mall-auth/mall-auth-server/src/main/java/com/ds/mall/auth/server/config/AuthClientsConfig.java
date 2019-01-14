package com.ds.mall.auth.server.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tb
 * @date 2019/1/11 14:08
 */
@Getter
@Setter
public class AuthClientsConfig {

    //注册到consul的服务名称（不区分大小）
    private String id;
    //数据库设定的服务密码
    private String secret;
    //超时
    private int expire;
    //RSA密码
    private String password;
    //初始化之后生成的密钥对
    private byte[] pubKey;
    private byte[] priKey;
}

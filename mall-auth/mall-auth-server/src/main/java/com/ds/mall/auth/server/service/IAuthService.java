package com.ds.mall.auth.server.service;

import com.ds.mall.auth.server.model.LoginRequest;

/**
 * 客户端注册获取token
 * @author tb
 * @date 2019/1/10 14:32
 */
public interface IAuthService {

    /**
     * 登录获取token
     */
    String login(LoginRequest request) throws Exception;

    /**
     * 刷新token
     */
    String refresh(String oldToken) throws Exception;

    /**
     * 校验token的合法性
     */
    void verify(String token) throws Exception;
}

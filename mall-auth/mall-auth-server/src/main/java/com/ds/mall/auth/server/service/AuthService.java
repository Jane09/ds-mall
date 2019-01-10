package com.ds.mall.auth.server.service;

import com.ds.mall.auth.server.model.LoginRequest;

/**
 * @author tb
 * @date 2019/1/10 14:32
 */
public interface AuthService {

    String login(LoginRequest request) throws Exception;
    String refresh(String oldToken) throws Exception;
    void validate(String token) throws Exception;
}

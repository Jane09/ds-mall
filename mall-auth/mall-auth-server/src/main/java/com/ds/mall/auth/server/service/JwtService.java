package com.ds.mall.auth.server.service;

import com.ds.mall.auth.common.jwt.IJwtData;
import com.ds.mall.auth.common.jwt.JwtData;
import com.ds.mall.auth.common.jwt.JwtUtils;
import com.ds.mall.auth.server.config.AuthConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author tb
 * @date 2019/1/11 14:23
 */
@Component
@RequiredArgsConstructor
public class JwtService {

    private final AuthConfig authConfig;

    public String buildClientToken(String clientId,String clientName,String username) throws Exception {
        JwtData data = new JwtData(clientId,clientName,username);
        return JwtUtils.generateToken(data,authConfig.getClient().getPriKey(),authConfig.getClient().getExpire());
    }

    public IJwtData getClientFromToken(String token) throws Exception {
        return JwtUtils.getInfoFromToken(token,authConfig.getClient().getPubKey());
    }
}

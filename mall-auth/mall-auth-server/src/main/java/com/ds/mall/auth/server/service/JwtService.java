package com.ds.mall.auth.server.service;

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


}

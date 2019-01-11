package com.ds.mall.auth.server.controller;

import com.ds.mall.auth.server.config.AuthConfig;
import com.ds.mall.auth.server.model.LoginRequest;
import com.ds.mall.auth.server.service.IAuthService;
import com.ds.mall.common.response.ObjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Token发放
 * @author tb
 * @date 2019/1/10 14:10
 */
@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings({"unchecked"})
public class AuthController {

    private final IAuthService authService;
    private final AuthConfig authConfig;

    /**
     * 登录获取token
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ObjectResponse<String> login(@RequestBody LoginRequest request) throws Exception {
        log.info("{} require login...",request.getUsername());
        return new ObjectResponse<String>().data(authService.login(request));
    }

    /**
     * 刷新
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ObjectResponse<String> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws Exception {
        String token = request.getHeader(authConfig.getJwt().getTokenHeader());
        String refreshedToken = authService.refresh(token);
        return new ObjectResponse<>().data(refreshedToken);
    }

    /**
     * 校验
     */
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public ObjectResponse<?> verify(String token) throws Exception {
        authService.verify(token);
        return new ObjectResponse<>();
    }
}

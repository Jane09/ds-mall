package com.ds.mall.auth.server.service;

import com.ds.mall.auth.server.feign.FeignApi;
import com.ds.mall.auth.server.model.LoginRequest;
import com.ds.mall.backend.vo.user.UserVo;
import com.ds.mall.common.exception.UserInvalidException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author tb
 * @date 2019/1/11 14:00
 */
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final FeignApi feignApi;

    @Override
    public String login(LoginRequest request) throws Exception {
        UserVo user = feignApi.login(request);
        if(null == user || StringUtils.isEmpty(user.getId())){
            throw new UserInvalidException("The user does not exist.");
        }
        return null;
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        return null;
    }

    @Override
    public void verify(String token) throws Exception {

    }
}

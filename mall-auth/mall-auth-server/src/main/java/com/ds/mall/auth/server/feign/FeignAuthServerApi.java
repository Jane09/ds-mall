package com.ds.mall.auth.server.feign;

import com.ds.mall.auth.server.model.LoginRequest;
import com.ds.mall.backend.vo.user.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tb
 * @date 2019/1/10 14:42
 */
@FeignClient(value = FeignAuthServerApi.LOGIN_SERVICE)
public interface FeignAuthServerApi {

    String LOGIN_SERVICE = "ace-admin";
    String LOGIN_URL = "/api/user/validate";

    @RequestMapping(value = LOGIN_URL, method = RequestMethod.POST)
    UserVo login(LoginRequest request);
}

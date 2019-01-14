package com.ds.mall.auth.server.interceptor;

import com.ds.mall.auth.server.config.AuthConfig;
import com.ds.mall.auth.server.service.ClientService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Feign访问的鉴权
 * @author tb
 * @date 2019/1/10 15:41
 */
@Slf4j
public class FeignInterceptor implements RequestInterceptor {

    @Autowired
    private AuthConfig authConfig;
    @Autowired
    private ClientService clientService;

    /**
     * 在请求头上添加 token-header，并且校验是否能够访问服务
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            requestTemplate.header(authConfig.getTokenHeader(),
                    clientService.buildToken(authConfig.getClient().getCode(), authConfig.getClient().getSecret()));
        } catch (Exception e) {
            log.error("Feign Request Header Token Generate Failed",e);
        }
    }
}

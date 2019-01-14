package com.ds.mall.auth.server.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Feign访问的鉴权
 * @author tb
 * @date 2019/1/10 15:41
 */
public class FeignInterceptor implements RequestInterceptor {



    /**
     * 在请求头上添加 token-header，并且校验是否能够访问服务
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {

    }
}

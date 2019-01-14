package com.ds.mall.auth.client.feign;

import com.ds.mall.common.response.ObjectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tb
 * @date 2019/1/10 16:12
 */
@FeignClient("${auth.serviceId}")
public interface FeignAuthClientApi {

    @RequestMapping(value = "/client/myClient")
    ObjectResponse<List<String>> getAllowedClient(@RequestParam("serviceId") String serviceId,
                                                  @RequestParam("secret") String secret);

    @RequestMapping(value = "/client/token", method = RequestMethod.POST)
    ObjectResponse getAccessToken(@RequestParam("clientId") String clientId,
                                  @RequestParam("secret") String secret);

    @RequestMapping(value = "/client/servicePubKey", method = RequestMethod.POST)
    ObjectResponse<byte[]> getServicePublicKey(@RequestParam("clientId") String clientId,
                                               @RequestParam("secret") String secret);

    @RequestMapping(value = "/client/userPubKey", method = RequestMethod.POST)
    ObjectResponse<byte[]> getUserPublicKey(@RequestParam("clientId") String clientId,
                                            @RequestParam("secret") String secret);
}

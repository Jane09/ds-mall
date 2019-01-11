package com.ds.mall.auth.server.service;

import java.util.List;

/**
 * @author tb
 * @date 2019/1/10 14:34
 */
public interface IClientsService {

    String buildToken(String clientId, String secret) throws Exception;

    /**
     * 获取授权的客户端列表
     */
    List<String> getAllowedClients(String serviceId, String secret);

    /**
     * 获取服务授权的客户端列表
     */
    List<String> getAllowedClients(String serviceId);

    /**
     * 校验
     */
    void verify(String clientId, String secret) throws Exception;

}

package com.ds.mall.auth.server.service;

import com.ds.mall.auth.server.mapper.ClientsMapper;
import com.ds.mall.auth.server.model.Clients;
import com.ds.mall.common.service.AbstractBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tb
 * @date 2019/1/10 15:25
 */
@Service
@RequiredArgsConstructor
public class ClientsService extends AbstractBaseService<ClientsMapper, Clients> implements IClientsService {

    private final ClientsMapper clientsMapper;


    @Override
    public String buildToken(String clientId, String secret) throws Exception {
        return null;
    }

    @Override
    public List<String> getAllowedClients(String serviceId, String secret) {
        return null;
    }

    @Override
    public List<String> getAllowedClients(String serviceId) {
        return null;
    }

    @Override
    public void verify(String clientId, String secret) throws Exception {

    }
}

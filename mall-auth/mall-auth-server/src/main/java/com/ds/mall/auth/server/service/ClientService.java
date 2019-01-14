package com.ds.mall.auth.server.service;

import com.ds.mall.auth.server.mapper.ClientsMapper;
import com.ds.mall.auth.server.model.Client;
import com.ds.mall.common.exception.ClientInvalidException;
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
public class ClientService extends AbstractBaseService<ClientsMapper, Client> implements IClientService {

    private final ClientsMapper clientsMapper;
    private final JwtService jwtService;


    @Override
    public String buildToken(String clientsId, String secret) throws Exception {
        Client client = getClients(clientsId,secret);
        return jwtService.buildClientToken(clientsId, client.getName(),String.valueOf(client.getId()));
    }

    @Override
    public List<String> getAllowedClients(String clientsId, String secret) {
        return null;
    }

    @Override
    public List<String> getAllowedClients(String servicesId) {
        return null;
    }

    @Override
    public void verify(String clientsId, String secret) throws Exception {
        getClients(clientsId,secret);
    }

    private Client getClients(String clientsId, String secret) {
        Client query = new Client();
        query.setCode(clientsId);
        Client client = clientsMapper.selectOne(query);
        if(null == clientsId || !secret.equals(client.getSecret())) {
            throw new ClientInvalidException("client not found or client secret is error");
        }
        return client;
    }
}

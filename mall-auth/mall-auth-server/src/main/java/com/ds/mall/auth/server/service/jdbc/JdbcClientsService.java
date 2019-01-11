package com.ds.mall.auth.server.service.jdbc;

import com.ds.mall.auth.server.service.IClientsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tb
 * @date 2019/1/10 14:41
 */
@Service
public class JdbcClientsService implements IClientsService {

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

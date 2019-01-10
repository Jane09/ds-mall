package com.ds.mall.auth.server.service;

import com.ds.mall.auth.server.mapper.ClientMapper;
import com.ds.mall.auth.server.model.Client;
import com.ds.mall.common.service.AbstractBaseService;

import java.util.List;

/**
 * @author tb
 * @date 2019/1/10 15:25
 */
public class ClientService extends AbstractBaseService<ClientMapper, Client> {

    @Override
    public List<Client> selectListByIds(List<Object> ids) {
        return null;
    }

    @Override
    public Long selectCountAll() {
        return null;
    }

    @Override
    public void deleteBatchByIds(List<Object> ids) {

    }

    @Override
    public void updateBatch(List<Client> entitys) {

    }
}

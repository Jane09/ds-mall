package com.ds.mall.auth.server.mapper;

import com.ds.mall.auth.server.model.ClientAccess;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author tb
 * @date 2019/1/10 15:34
 */
public interface ServicesMapper extends Mapper<ClientAccess> {

    void delByServiceId(String serviceId);
}

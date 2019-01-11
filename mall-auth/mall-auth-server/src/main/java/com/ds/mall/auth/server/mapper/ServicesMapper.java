package com.ds.mall.auth.server.mapper;

import com.ds.mall.auth.server.model.Services;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author tb
 * @date 2019/1/10 15:34
 */
public interface ServicesMapper extends Mapper<Services> {

    void delByServiceId(String serviceId);
}

package com.ds.mall.auth.client.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tb
 * @date 2019/1/10 16:12
 */
@FeignClient("${auth.serviceId}")
public interface ServiceAuthFeign {


}

package com.ds.mall.gate.feign;

import com.ds.mall.gate.fallback.UserFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tb
 * @date 2019/1/14 11:31
 */
@FeignClient(value = FeignConstant.S_MALL_BACKEND, fallback = UserFeignFallback.class)
public interface UserFeign {

}

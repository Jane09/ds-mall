package com.ds.mall.gate.feign;

import com.ds.mall.backend.vo.log.LogVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tb
 * @date 2019/1/14 11:31
 */
@FeignClient(value = FeignConstant.S_MALL_BACKEND)
public interface LogFeign {

    @RequestMapping(value="/api/log/save",method = RequestMethod.POST)
    void saveLog(LogVo log);
}

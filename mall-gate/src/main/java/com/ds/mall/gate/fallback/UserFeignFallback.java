package com.ds.mall.gate.fallback;

import com.ds.mall.gate.feign.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tb
 * @date 2019/1/14 11:32
 */
@Component
@Slf4j
public class UserFeignFallback implements UserFeign {
}

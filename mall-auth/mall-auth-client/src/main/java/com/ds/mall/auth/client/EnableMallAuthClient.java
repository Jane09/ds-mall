package com.ds.mall.auth.client;

import com.ds.mall.auth.client.config.AutoAuthConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author tb
 * @date 2019/1/10 16:08
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoAuthConfig.class)
@Documented
@Inherited
public @interface EnableMallAuthClient {
}

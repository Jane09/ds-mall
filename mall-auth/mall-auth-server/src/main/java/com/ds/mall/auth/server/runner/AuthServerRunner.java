package com.ds.mall.auth.server.runner;

import com.ds.mall.auth.server.config.AuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @author tb
 * @date 2019/1/10 14:17
 */
@Configuration
public class AuthServerRunner implements CommandLineRunner {

    @Autowired
    private AuthConfig authConfig;

    @Override
    public void run(String... args) throws Exception {

    }
}

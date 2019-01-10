package com.ds.mall.uid;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author tb
 * @date 2019/1/10 9:54
 */
@SpringBootApplication
public class UidApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UidApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}

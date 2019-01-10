package com.ds.mall.uid;

import com.ds.mall.uid.config.UidConfig;
import com.ds.mall.uid.remote.thrift.ThriftModule;
import com.ds.mall.uid.remote.thrift.ThriftService;
import com.ds.mall.uid.zookeeper.ZookeeperModule;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

/**
 * @author tb
 * @date 2019/1/10 9:54
 */
@SpringBootApplication
public class UidApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = new SpringApplicationBuilder(UidApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
        UidConfig uidConfig = ctx.getBean(UidConfig.class);
        ThriftService thriftService = ctx.getBean(ThriftService.class);
        new ZookeeperModule(uidConfig).start();
        new ThriftModule(uidConfig).service(thriftService).start();
    }
}

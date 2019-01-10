package com.ds.mall.uid.zookeeper;

import com.ds.mall.uid.AbstractModule;
import com.ds.mall.uid.config.UidConfig;
import com.ds.mall.uid.config.ZookeeperConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.http.util.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tb
 * @date 2019/1/9 17:25
 */
@Slf4j
public class ZookeeperModule extends AbstractModule {

    private CuratorFramework conn;

    public ZookeeperModule(UidConfig uidConfig) {
        super(uidConfig);
    }

    @Override
    protected void doStart() throws Exception {
        log.info("启动zookeeper");
        init();
        register();
    }


    private void init() {
        try{
            AuthInfo authInfo = new AuthInfo();
            CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
            builder.connectString(getConnString())
                    .connectionTimeoutMs(uidConfig.getZookeeper().getConnectionTimeoutMs())
                    .sessionTimeoutMs(uidConfig.getZookeeper().getSessionTimeoutMs())
                    .retryPolicy(new LeafBoundedExponentialBackoffRetry(
                            uidConfig.getZookeeper().getRetryIntervalMs(),
                            uidConfig.getZookeeper().getRetryIntervalceilingMs(),
                            uidConfig.getZookeeper().getRetryTimes()));
            if(!StringUtils.isEmpty(authInfo.getScheme()) && null != authInfo.getPayload()) {
                builder = builder.authorization(authInfo.getScheme(),authInfo.getPayload());
            }
            conn = builder.build();
        }catch (Exception ex) {
            log.error("初始化zookeeper连接失败",ex);
        }
    }


    private void register(){
        ZookeeperConfig config = this.uidConfig.getZookeeper();
        String root = config.getRoot();
        String pseq = config.getPersistent();
        String ipport = "ip:port";
    }


    private String getConnString() {
        List<String> servers = uidConfig.getZookeeper().getServers();
        List<String> conns = new ArrayList<>();
        servers.forEach(s -> conns.add(s+":"+uidConfig.getZookeeper().getPort()));
        return StringUtils.join(conns,",");
    }
}

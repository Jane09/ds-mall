package com.ds.mall.uid.zookeeper;

import com.ds.mall.common.utils.DateUtils;
import com.ds.mall.common.utils.NetUtils;
import com.ds.mall.uid.AbstractModule;
import com.ds.mall.uid.IdGenerator;
import com.ds.mall.uid.config.UidConfig;
import com.ds.mall.uid.config.ZookeeperConfig;
import com.ds.mall.uid.exception.MallZookeeperException;
import com.ds.mall.uid.remote.thrift.ThriftClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.zookeeper.CreateMode;
import org.omg.CORBA.INTERNAL;
import sun.nio.ch.Net;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * /ds-mall
 *      /p
 *          /ip:port - workId
 *          /ip:port - workId
 *      /pseq
 *          /pnode
 *      /eseq
 *          /ip:port - timestamp
 *          /ip:port - timestamp
 *      先校验持久化节点是否有 workId,
 *      没有则，新增 持久化顺序节点，产生一个顺序workId，成功后保存至 持久化节点;
 *      有则校验，周期性上传的 ip:port - timestamp < 当前时间戳
 *      如果不存在，创建
 *      得到所有 eseq ip:port集合
 *      avg(sum（timestamp))
 *
 *
 * @author tb
 * @date 2019/1/9 17:25
 */
@Slf4j

public class ZookeeperModule extends AbstractModule {

    private static final String SLASH = "/";
    private static final byte[] EMPTY = new byte[0];
    private static final String COLON = ":";
    private final ScheduledExecutorService executor =new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory
                    .Builder()
                    .namingPattern("mall-report-timestamp-task")
                    .daemon(true)
                    .build());

    private CuratorFramework conn;

    public ZookeeperModule(UidConfig uidConfig) {
        super(uidConfig);
    }

    @Override
    protected void doStart() throws Exception {
        log.info("启动zookeeper");
        init();
        register();
        //启动定时上传
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


    private void register() throws Exception {
        ZookeeperConfig config = this.uidConfig.getZookeeper();
        String root = config.getRoot();
        String persist = config.getPersistent();
        String ephemeral = config.getEphemeral();
        String ipport = StringUtils.join(NetUtils.getInternetIp(),COLON,uidConfig.getPort());
        String path = StringUtils.join(SLASH,root,SLASH,persist,SLASH,ipport);
        String tmpath = StringUtils.join(SLASH,root,SLASH,ephemeral,SLASH,ipport);
        Integer workId;
        if(ZookeeperUtils.exists(conn,path,false)){
            byte[] data = ZookeeperUtils.getData(conn,path,false);
            if(data == null) {
                workId = getWorkId(path);
                ZookeeperUtils.setData(conn,path,NetUtils.intToBytes(workId));
            }else {
                workId = NetUtils.bytesToint(data);
            }
            //校验上次上传时间
            byte[] timestamp = ZookeeperUtils.getData(conn,tmpath,false);
            if(timestamp != null){
               Long last= NetUtils.bytesToLong(timestamp);
               if(last > getNow()) {
                   throw new MallZookeeperException("上次上传时间戳大约当前时间");
               }
            }
        }else {
            workId = getWorkId(path);

        }
        if(!ZookeeperUtils.exists(conn,tmpath,false)){
            ZookeeperUtils.addNode(conn,tmpath,NetUtils.longToBytes(getNow()),CreateMode.EPHEMERAL);
        }
        //ID生成器
        IdGenerator generator = new IdGenerator(workId,uidConfig.getDatacenter());
        System.out.println(generator.nextId());
        //校验平均时间戳
        checkAvgTimestamp(StringUtils.join(SLASH,root,SLASH,ephemeral),ipport);
        //启动定时上报时间戳
        executor.scheduleAtFixedRate(() -> {
            try {
                ZookeeperUtils.setData(conn,tmpath,NetUtils.longToBytes(getNow()));
            } catch (Exception e) {
                log.error("上传{}时间戳失败",tmpath);
            }
        },3L,3L, TimeUnit.SECONDS);
    }

    private void checkAvgTimestamp(String tmpath,String addr) throws Exception {
        List<String> children = ZookeeperUtils.getChildren(conn,tmpath,false);
        if(children != null && children.size() >1){
            List<String> addrs = new ArrayList<>();
            children.forEach(url -> {
                if(!url.equals(addr)) {
                    addrs.add(url);
                }
            });
            LongAdder adder = new LongAdder();
            int size = addrs.size();
            if(size >0) {
                addrs.forEach(ipport -> {
                    String[] ippa = ipport.split(COLON);
                    long timestamp = ThriftClient.getTimestamp(ippa[0],Integer.parseInt(ippa[1]));
                    adder.add(timestamp);
                });
                long avgTime = adder.longValue()/size;
                if(Math.abs(avgTime - getNow()) > uidConfig.getZookeeper().getAverageTimestampThreshold()) {
                    log.error("Current server timestamp :{} does not match the average timestamp: {}",
                            DateUtils.format(getNow(),DateUtils.DATE_TIME_PATTERN),
                            DateUtils.format(avgTime, DateUtils.DATE_TIME_PATTERN));
                    throw new MallZookeeperException("校验平均时间戳失败");
                }
            }

        }
    }

    private long getNow() {
        return Instant.now().toEpochMilli();
    }


    private Integer getWorkId(String path) throws Exception {
        String seqPath = ZookeeperUtils.addNode(conn,path,EMPTY, CreateMode.PERSISTENT_SEQUENTIAL);
        seqPath = seqPath.replace(path,"");
        Integer workId = Integer.parseInt(seqPath);
        if(workId == 0){
            workId += 1;
        }
        ZookeeperUtils.addNode(conn,path,NetUtils.intToBytes(workId),CreateMode.PERSISTENT);
        return workId;
    }


    private String getConnString() {
        List<String> servers = uidConfig.getZookeeper().getServers();
        List<String> conns = new ArrayList<>();
        servers.forEach(s -> conns.add(s+":"+uidConfig.getZookeeper().getPort()));
        return StringUtils.join(conns,",");
    }
}

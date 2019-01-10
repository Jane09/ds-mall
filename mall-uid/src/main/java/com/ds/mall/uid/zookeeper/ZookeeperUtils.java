package com.ds.mall.uid.zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author tb
 * @date 2019/1/10 11:05
 */
public final class ZookeeperUtils {

    public static void main(String[] args) throws Exception {
        CuratorFramework conn = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .connectionTimeoutMs(5000)
            .sessionTimeoutMs(12000)
            .retryPolicy(new BoundedExponentialBackoffRetry(2000,3000,3)).build();
        conn.start();
//        System.out.println(addNode(conn,"/mall/uid",new byte[0],CreateMode.PERSISTENT_SEQUENTIAL));
        List<String> children = getChildren(conn,"/mall",false);
        System.out.println(JSON.toJSONString(children));
        System.out.println(exists(conn,"/mall/uid0000000000",false));
        conn.close();
    }

    /**
     * 判断节点是否存在
     */
    public static boolean exists(CuratorFramework conn, String path, boolean watched) throws Exception {
        Stat stat;
        if(watched) {
            stat = conn.checkExists().watched().forPath(path);
        }else {
            stat = conn.checkExists().forPath(path);
        }
//        if(null != stat) {
//            System.out.println(JSON.toJSONString(stat));
//        }
        return null != stat;
    }

    public static String addNode(CuratorFramework conn, String path, byte[] data, CreateMode mode) throws Exception {
        if(exists(conn,path,false)) {
            return path;
        }
        return conn.create().creatingParentsIfNeeded().withMode(mode).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path,data);
    }

    public static List<String> getChildren(CuratorFramework conn, String path, boolean watched) throws Exception {
        if(!exists(conn,path,watched)){
            return null;
        }
        if(watched) {
            return conn.getChildren().watched().forPath(path);
        }
        return conn.getChildren().forPath(path);
    }

    public static byte[] getData(CuratorFramework conn, String path, boolean watched) throws Exception {
        if(!exists(conn,path,watched)) {
            return null;
        }
        if(watched) {
            return conn.getData().watched().forPath(path);
        }else {
            return conn.getData().forPath(path);
        }
    }
}

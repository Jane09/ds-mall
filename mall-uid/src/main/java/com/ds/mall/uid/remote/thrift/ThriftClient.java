package com.ds.mall.uid.remote.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;

/**
 * @author tb
 * @date 2019/1/10 10:31
 */
public final class ThriftClient {

    public static long getTimestamp(String host, int port) {
        TSocket transport = new TSocket(host, port);
        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        RPCService.Client client = new RPCService.Client(protocol);
        try {
            transport.open();
            return client.getTimestamp(System.currentTimeMillis());
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }
        return 0L;
    }
}

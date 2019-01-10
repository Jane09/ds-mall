package com.ds.mall.uid.thrift;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

/**
 * @author tb
 * @date 2019/1/10 10:28
 */
@Service
public class ThriftService implements RPCService.Iface {

    //最大允许的偏差
    private static final long MAX_OFFSET = 5000L;

    @Override
    public long getTimestamp(long myTimestamp) throws TException {
        long timestamp = System.currentTimeMillis();
        long offset = Math.abs(timestamp - myTimestamp);
        if(MAX_OFFSET < offset){
            return -1;
        }
        return timestamp;
    }

    @Override
    public String getId(String param) throws TException {
        return null;
    }
}

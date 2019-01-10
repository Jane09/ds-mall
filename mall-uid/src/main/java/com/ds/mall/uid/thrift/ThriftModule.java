package com.ds.mall.uid.thrift;

import com.ds.mall.uid.AbstractModule;
import com.ds.mall.uid.config.UidConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;

/**
 * @author tb
 * @date 2019/1/10 9:48
 */
@Slf4j
public class ThriftModule extends AbstractModule {

    private ThriftService thriftService;

    public ThriftModule(UidConfig uidConfig) {
        super(uidConfig);
    }
    public ThriftModule service(ThriftService thriftService) {
        this.thriftService = thriftService;
        return this;
    }

    @Override
    protected void doStart() throws Exception {
        log.info("启动Thrift");
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
        TTransportFactory transportFactory = new TTransportFactory();
        RPCService.Processor processor = new RPCService.Processor<RPCService.Iface>( thriftService );
        TServer server = null;
        try {
            TServerTransport transport = new TServerSocket(uidConfig.getPort());
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(transport);
            tArgs.processor(processor);
            tArgs.protocolFactory(protocolFactory);
            tArgs.transportFactory(transportFactory);
            tArgs.minWorkerThreads(uidConfig.getMinWorkerThreads());
            tArgs.maxWorkerThreads(uidConfig.getMaxWorkerThreads());
            server = new TThreadPoolServer(tArgs);
            started = true;
            server.serve();
        } catch (Exception e) {
            log.error("thrift服务启动失败", e);
            if(null != server) {
                server.stop();
            }
        }finally {
            if(isStarted()) {
                log.info("thrift服务启动成功, 端口={}", uidConfig.getPort());
            }
        }
    }


}

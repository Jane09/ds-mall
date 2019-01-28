package com.ds.mall.algorithm.nc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class NIO {
    private static volatile boolean start = false;
    public static void main(String[] args) {
        new Thread(() -> {
            Server server = new Server();
            try {
                server.init(8000);
                server.listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            Client client = new Client();
            try {
                client.init("localhost",8000);
                client.listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    static class Server {

        private Selector selector;

        public void init(int port) throws IOException {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(port));
            this.selector = Selector.open();
            channel.register(this.selector, SelectionKey.OP_ACCEPT);
        }

        public void listen() throws IOException {
            System.out.println("nio server start successfully");
            for(;;){
                this.selector.select();
                Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
                while (it.hasNext()){
                    SelectionKey key = it.next();
                    it.remove();
                    if(key.isAcceptable()){
                        ServerSocketChannel selectableChannel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = selectableChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.write(ByteBuffer.wrap("hello client".getBytes(Charset.defaultCharset())));
                        socketChannel.register(this.selector, SelectionKey.OP_READ);
                    }else if(key.isReadable()){
                        read("server",key);
                    }
                }
            }
        }
    }

    static class Client {
        private AtomicInteger threshold = new AtomicInteger(0);
        public Selector selector;

        public void init(String ip, int port) throws IOException {
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            this.selector = Selector.open();
            channel.connect(new InetSocketAddress(ip,port));
            channel.register(this.selector, SelectionKey.OP_CONNECT);
        }

        public void listen() throws IOException {
            for(;;){
                if(threshold.get() > 10) {
                    break;
                }
                this.selector.select();
                Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if(key.isConnectable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        if(channel.isConnectionPending()){
                            channel.finishConnect();
                        }
                        channel.configureBlocking(false);
                        channel.write(ByteBuffer.wrap("hello server".getBytes(Charset.defaultCharset())));
                        channel.register(this.selector,SelectionKey.OP_READ);
                    }else if(key.isReadable()){
                        read("client",key);
                    }
                }
                threshold.incrementAndGet();
            }
        }


    }
    private static void read(String node,SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        byte[] data = buffer.array();
        String message = new String(data).trim();
        System.out.println(node+ " received message:  "+message);
        ByteBuffer bb = ByteBuffer.wrap(message.getBytes(Charset.defaultCharset()));
        channel.write(bb);
    }
}

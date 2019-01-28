package com.ds.mall.algorithm.nc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AIO {

    public static void main(String[] args) {
        new Thread(() -> {
            Server server = new Server();
            try {
                server.start("0.0.0.0",8013);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            Client client = new Client();
            try {
                client.start("127.0.0.1",8013);
            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class Server {
        private static final Charset CHARSET = Charset.forName("US-ASCII");
        private static final CharsetEncoder ENCODER = CHARSET.newEncoder();
        public void start(String ip, int port) throws IOException, InterruptedException {
            AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(4));
            AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(ip, port));
            channel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
                @Override
                public void completed(AsynchronousSocketChannel result, Void attachment) {
                    channel.accept(null,this);
                    String now = LocalDateTime.now().toString();
                    try {
                        ByteBuffer buffer = ENCODER.encode(CharBuffer.wrap(now+"\r\n"));
                        Future<Integer> f = result.write(buffer);
                        f.get();
                        System.out.println("Send to client: "+now);
                        result.close();
                    } catch (InterruptedException | ExecutionException | IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    exc.printStackTrace();
                }
            });
            group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        }

    }


    static class Client {
        public void start(String ip, int port) throws ExecutionException, InterruptedException, IOException {
            AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
            Future<Void> future = client.connect(new InetSocketAddress(ip, port));
            future.get();
            ByteBuffer buffer = ByteBuffer.allocate(100);
            client.read(buffer, null, new CompletionHandler<Integer, Void>() {
                @Override
                public void completed(Integer result, Void attachment) {
                    System.out.println("client received: " + new String(buffer.array()));
                }
                @Override
                public void failed(Throwable exc, Void attachment) {
                    exc.printStackTrace();
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            Thread.sleep(10000);
        }
    }
}

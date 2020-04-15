package com.adc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();

        SocketAddress address = new InetSocketAddress("localhost", 8080);
        server.socket().bind(address);
        server.configureBlocking(false);

        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 查询IO事件
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    // 建立新连接
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);

                    // 注册channel到selector上，订阅IO可读事件
                    channel.register(selector, SelectionKey.OP_READ);
                }
            }
        }
    }
}

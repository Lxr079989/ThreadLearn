package cn.itcast.netty.c1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {
    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            // 建立连接
            socketChannel.connect(new InetSocketAddress("localhost", 8080));
            socketChannel.write(Charset.defaultCharset().encode("hellkhiuhiuhiuhihiuhiuworld\n"));
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

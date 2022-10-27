package cn.itcast.netty.c1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Demo1 {
    public static void main(String[] args) throws IOException {
        Thread thread1 = new Thread(()->{
            try {
                server();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(()->{
            try {
                client();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

    public static void client() throws IOException {
        // 创建客户端通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8988));

        // 读取信息
        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/liuxinrong/IdeaProjects/jim/netty/data2.txt"), StandardOpenOption.READ);

        // 创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 写入数据
        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        fileChannel.close();
        socketChannel.close();
    }

    public static void server() throws IOException {
        // 创建服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/liuxinrong/IdeaProjects/jim/netty/data.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // 绑定链接
        serverSocketChannel.bind(new InetSocketAddress(8988));

        // 获取客户端的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (socketChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        socketChannel.close();
        fileChannel.close();
        serverSocketChannel.close();
    }
}
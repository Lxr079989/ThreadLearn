package cn.itcast.netty.c4;

import io.netty.channel.local.LocalAddress;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class WriteClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8848);
        InputStream inputStream = socket.getInputStream();
        byte[] arr = new byte[10];
        inputStream.read(arr);
        System.out.println(new String(arr));
        inputStream.close();
        socket.close();
    }
}

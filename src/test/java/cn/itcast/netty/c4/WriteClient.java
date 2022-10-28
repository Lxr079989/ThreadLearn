package cn.itcast.netty.c4;

import io.netty.channel.local.LocalAddress;

import java.io.ByteArrayOutputStream;
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
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int offset = -1;
        byte[] buffer = new byte[1024];
        while ((offset = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, offset);
        }
        inputStream.close();
        System.out.println(byteArrayOutputStream);
        socket.close();
    }
}

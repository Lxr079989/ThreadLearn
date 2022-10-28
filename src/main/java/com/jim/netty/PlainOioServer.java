package com.jim.netty;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class PlainOioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8848);
        while (true){
            Socket accept = serverSocket.accept();
            log.info("收到客户端连接请求："+accept);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OutputStream outputStream = accept.getOutputStream();
                        outputStream.write("Hijfhcsdlajfhlasdfuihiasdfhkljasdhfljkahsdfkjashfkjashdf;kajshf;kjashdf;kajsdhf;kjadhgksdjfhgjfksdvh;fksjdvbdfv".getBytes(StandardCharsets.UTF_8));
                        outputStream.flush();
                        outputStream.write("22222Hijfhcsdlajfhlasdfuihiasdfhkljasdhfljkahsdfkjashfkjashdf;kajshf;kjashdf;kajsdhf;kjadhgksdjfhgjfksdvh;fksjdvbdfv".getBytes(StandardCharsets.UTF_8));
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            accept.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            },"Server-Thread").start();
        }
    }
}

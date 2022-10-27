package cn.itcast.netty.c1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Demo4 {
    public static void main(String[] args) {
        FileInputStream is = null;
        FileOutputStream os = null;
        // 获得通道
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        // 利用 try-catch-finally 保证关闭
        try {
            is = new FileInputStream("/Users/liuxinrong/IdeaProjects/jim/netty/data.txt");
            os = new FileOutputStream("/Users/liuxinrong/IdeaProjects/jim/netty/data2.txt");

            // 获得通道
            inChannel = is.getChannel();
            outChannel = os.getChannel();

            // 获得缓冲区，用于在通道中传输数据
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 循环将字节数据放入到buffer中，然后写入磁盘中
            while (inChannel.read(byteBuffer) != -1) {
                // 切换模式
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

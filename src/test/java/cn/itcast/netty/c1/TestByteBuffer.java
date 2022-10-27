package cn.itcast.netty.c1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestByteBuffer {

    public static void main(String[] args) {
        try (FileChannel channel = new FileOutputStream("data.txt").getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(10);
            buffer.put(new byte[]{0x62,0x63,0x64});
            channel.write(buffer);
            buffer.clear();
//            while (true) {
//                int len = channel.read(buffer);
//                if (len == -1) {
//                    break;
//                }
//
//                buffer.flip();
//                while (buffer.hasRemaining()) {
//                    byte b = buffer.get();
//                    System.out.println((char) b);
//                }
//                buffer.clear();
//            }

        } catch (IOException e) {

        }
    }
}

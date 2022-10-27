package cn.itcast.netty.c1;

import java.nio.ByteBuffer;
import static cn.itcast.netty.c1.ByteBufferUtil.debugAll;

public class TestByteBufferByRead {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a','b','c','d'});
        buffer.flip();
        buffer.get(new byte[4]);
        debugAll(buffer);
        buffer.rewind();
        System.out.println((char)buffer.get());
        debugAll(buffer);
    }
}

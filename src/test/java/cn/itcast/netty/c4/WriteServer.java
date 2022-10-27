package cn.itcast.netty.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

public class WriteServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel.bind(new InetSocketAddress(8080));

        while (true){
            selector.select();
            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();

            while (selectionKeyIterator.hasNext()){
                SelectionKey selectionKey = selectionKeyIterator.next();
                selectionKeyIterator.remove();
                if(selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    SelectionKey selectionKey1 = socketChannel.register(selector, 0, null);
                    selectionKey1.interestOps(SelectionKey.OP_READ);
                    // send much data
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 3000000; i++) {
                        sb.append("a");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());
                    int write = socketChannel.write(buffer);
                    System.out.println(write);
                    // 发送的时候需要创建bytebuffer去传输数据

                    if (buffer.hasRemaining()){
                        selectionKey1.interestOps(selectionKey1.interestOps()+SelectionKey.OP_WRITE);
                        selectionKey1.attach(buffer);
                    }
                }else if(selectionKey.isWritable()){
                    ByteBuffer buffer = (ByteBuffer)selectionKey.attachment();
                    SocketChannel sc = (SocketChannel)selectionKey.channel();
                    int write = sc.write(buffer);
                    System.out.println(write);
                    if(!buffer.hasRemaining()){
                        selectionKey.attach(null);
                        selectionKey.interestOps(selectionKey.interestOps()-SelectionKey.OP_WRITE);
                    }
                }
            }
        }

    }
}

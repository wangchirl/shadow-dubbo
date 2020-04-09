package com.shadow.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NewIO3 {
    public static void main(String[] args) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("NewIO3.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] bytes = "Hello world".getBytes();
        // 读取内容到Buffer中
        for (int i = 0; i < bytes.length; i++) {
            byteBuffer.put(bytes[i]);
        }
        // 反转
        byteBuffer.flip();
        // 从channel中写出
        channel.write(byteBuffer);
        fileOutputStream.close();

    }
}

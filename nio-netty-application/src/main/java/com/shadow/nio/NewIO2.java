package com.shadow.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NewIO2 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("Demo.txt");
        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        // channel读取数据到buffer
        channel.read(byteBuffer);
        // 反转
        byteBuffer.flip();
        // 从buffer中获取数据
        while (byteBuffer.remaining() > 0){
            byte b = byteBuffer.get();
            System.out.println("Char ==> " +(char) b);
        }
        fileInputStream.close();
    }
}

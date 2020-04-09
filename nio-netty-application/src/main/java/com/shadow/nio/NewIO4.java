package com.shadow.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NewIO4 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);

        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        while (true){
            // 这里的clear并不是真正的清空buffer，而是移动position和limit位置
            // 那为什么最后一次读取的字节树可能不够capacity，而没有将上一次读取到的值写到文件中呢？
            // 因为flip之后，limit的位置是读取到的position位置了啊，这样后面的字节就不会被写入了啊
            byteBuffer.clear();

            int i = channel.read(byteBuffer);
            System.out.println(i);
            System.out.println((char) byteBuffer.get(2));
            if(i == -1){
                break;
            }

            byteBuffer.flip();
            System.out.println(byteBuffer.limit());
            outputStreamChannel.write(byteBuffer);
        }
        fileOutputStream.close();
        fileInputStream.close();
    }
}

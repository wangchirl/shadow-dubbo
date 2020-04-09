package com.shadow.bio;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class BlockingIO {
    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream("README.md");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
        byte[] bytes = new byte[1024];
        dataInputStream.read(bytes);
        dataInputStream.close();
    }
}

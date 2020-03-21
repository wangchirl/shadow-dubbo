package com.shadow.javaspi;

import com.shadow.javaspi.service.Color;

import java.util.Iterator;
import java.util.ServiceLoader;

public class JavaSPI {

    public static void main(String[] args) {
        /**
         * 1. 读取 META-INF/services下配置的interface名称的文件
         * 2. 读取到文件的内容，文件中配置的是interfacce的具体实现类
         * 3. 利用反射机制，实例化对象
         *
         *  JAVA SPI机制==> ServiceLoader
         *  缺点：
         *  1. 不能指定只获取部分的实现
         *  2. 不支持IOC和AOP
         */
        ServiceLoader serviceLoader = ServiceLoader.load(Color.class);
        Iterator<Color> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            Color color = iterator.next();
            color.printColor();
        }
    }

}

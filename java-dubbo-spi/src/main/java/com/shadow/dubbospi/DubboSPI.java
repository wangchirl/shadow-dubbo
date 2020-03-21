package com.shadow.dubbospi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.shadow.dubbospi.service.Car;
import com.shadow.dubbospi.service.ioc.Person;

import java.util.HashMap;
import java.util.Map;

public class DubboSPI {
    public static void main(String[] args) {
        /**
         *  Dubbo SPI机制
         *  1. 接口上添加 @SPI 注解
         *  2. META-INF/service 下创建接口名称的文件
         *  3. 使用键值对的方式写入实现类
         *  4. 使用 ExtensionLoader 来获取
         *
         *  AOP 功能实现：
         *  1. 编写 接口的实现类 Wrapper，注入接口的类，在Wrapper中调用接口的方法，前后可以添加内容
         *  2. 将Wrapper 类也配置到 META-INF/service下的接口文件中
         *
         */
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);
        Car redFlagCar = extensionLoader.getExtension("redFlag");
        redFlagCar.carName();

        Car aodi = extensionLoader.getExtension("audi");
        aodi.carName();

        /**
         *  IOC 功能的实现：Person -> SuperMan
         * 1. 接口必须使用 URL @SPI @Adaptive指定map中的类型key
         * 2. 提供 set 方法
         */
        ExtensionLoader<Person> loader = ExtensionLoader.getExtensionLoader(Person.class);
        Person superman = loader.getExtension("superman");
        Map map = new HashMap();
        map.put("carType", "redFlag");
        URL url = new URL("", "", 0, map);
        superman.driverCar(url);
    }
}

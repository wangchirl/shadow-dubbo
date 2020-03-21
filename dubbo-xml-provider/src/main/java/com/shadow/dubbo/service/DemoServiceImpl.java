package com.shadow.dubbo.service;

import com.shadow.dubbo.DemoService;

public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        System.out.println("服务调用成功" + name);
        return "Hello " + name + ", response from provider";
    }
}

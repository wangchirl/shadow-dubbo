package provider.impl;

import provider.api.HelloService;

public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        System.out.println("服务调用成功===>" + name);
        return "Hello " + name;
    }
}

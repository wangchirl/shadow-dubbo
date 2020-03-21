package com.shadow.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println(demoService);
        String result = demoService.sayHello("shadow");
        System.out.println("result============" + result);
    }
}

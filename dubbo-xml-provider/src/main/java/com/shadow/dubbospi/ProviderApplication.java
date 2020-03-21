package com.shadow.dubbospi;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class ProviderApplication {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        context.start();
        DemoService bean = context.getBean(DemoService.class);
        System.out.println(bean);
        System.in.read();
    }
}

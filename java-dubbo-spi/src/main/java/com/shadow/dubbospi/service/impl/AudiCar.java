package com.shadow.dubbospi.service.impl;

import com.alibaba.dubbo.common.URL;
import com.shadow.dubbospi.service.Car;

public class AudiCar implements Car {
    @Override
    public void carName() {
        System.out.println("aodi car");
    }

    @Override
    public void run(URL url) {
        System.out.println("audi car running...");
    }
}

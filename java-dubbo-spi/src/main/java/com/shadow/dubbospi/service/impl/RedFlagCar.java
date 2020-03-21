package com.shadow.dubbospi.service.impl;

import com.alibaba.dubbo.common.URL;
import com.shadow.dubbospi.service.Car;

public class RedFlagCar implements Car {
    @Override
    public void carName() {
        System.out.println("red flag car");
    }

    @Override
    public void run(URL url) {
        System.out.println("red car running....");
    }
}

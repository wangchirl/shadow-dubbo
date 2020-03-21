package com.shadow.dubbospi.service.aop;

import com.alibaba.dubbo.common.URL;
import com.shadow.dubbospi.service.Car;

public class CarWrapper implements Car {

    private Car car;

    public CarWrapper(Car car){
        this.car = car;
    }

    @Override
    public void carName() {
        System.out.println("before car name");
        car.carName();
        System.out.println("after car name");
    }

    @Override
    public void run(URL url) {
        System.out.println("before run");
        car.run(url);
        System.out.println("after run");
    }
}

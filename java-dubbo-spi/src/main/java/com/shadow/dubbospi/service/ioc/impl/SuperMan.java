package com.shadow.dubbospi.service.ioc.impl;

import com.alibaba.dubbo.common.URL;
import com.shadow.dubbospi.service.Car;
import com.shadow.dubbospi.service.ioc.Person;

public class SuperMan implements Person {

    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public void driverCar(URL url) {
        car.run(url);
    }
}

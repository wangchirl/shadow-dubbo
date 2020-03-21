package com.shadow.dubbospi.service.ioc;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface Person {
    void driverCar(URL url);
}

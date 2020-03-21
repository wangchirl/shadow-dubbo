package com.shadow.dubbospi.service;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface Car {
    void carName();

    @Adaptive(value = "carType")
    void run(URL url);
}

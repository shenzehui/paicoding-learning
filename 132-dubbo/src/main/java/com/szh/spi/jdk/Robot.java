package com.szh.spi.jdk;

import org.apache.dubbo.common.extension.SPI;

// 添加@SPI注解，表示该接口是一个扩展点
@SPI
public interface Robot {
    void sayHello();
}
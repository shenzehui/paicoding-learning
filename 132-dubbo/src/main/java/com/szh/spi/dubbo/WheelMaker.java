package com.szh.spi.dubbo;


import org.apache.dubbo.common.URL;

public interface WheelMaker {
    Wheel makeWheel(URL url);
}
package com.szh.spi.dubbo;

import org.apache.dubbo.common.URL;

public interface CarMaker {
    Car makeCar(URL url);
}

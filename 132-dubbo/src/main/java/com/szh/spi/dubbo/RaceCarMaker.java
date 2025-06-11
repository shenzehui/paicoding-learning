package com.szh.spi.dubbo;

import org.apache.dubbo.common.URL;

public class RaceCarMaker implements CarMaker {

    WheelMaker wheelMaker;

    // 通过 setter 注入 AdaptiveWheelMaker
    public void setWheelMaker(WheelMaker wheelMaker) {
        this.wheelMaker = wheelMaker;
    }

    public Car makeCar(URL url) {
        Wheel wheel = wheelMaker.makeWheel(url);
        return new RaceCar(wheel);
    }
}
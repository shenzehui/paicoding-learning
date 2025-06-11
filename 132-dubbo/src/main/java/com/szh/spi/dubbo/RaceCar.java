package com.szh.spi.dubbo;

public class RaceCar implements Car {
    private Wheel wheel;

    public RaceCar(Wheel wheel) {
        this.wheel = wheel;
    }
}

package com.szh;

import com.szh.spi.jdk.Robot;
import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

/**
 * Unit test for simple App.
 */
public class JavaSPITest {

    @Test
    public void sayHello() throws Exception {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }

}

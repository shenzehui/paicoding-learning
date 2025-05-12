package com.szh.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

@SpringBootApplication
@Slf4j
public class Application {

    public Application() {
        log.debug("---> debug start <----");
        log.info("---> info start <----");
        log.warn("---> warn start <----");
        log.error("---> error start <----");

        System.out.println("===>  System.out.println <====");

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
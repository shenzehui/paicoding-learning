package com.szh.async;

import com.szh.async.factory.AsyncFactory;
import com.szh.async.manager.AsyncManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
public class Application implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("===主线程运行开始===");
        Thread.sleep(1000);
        AsyncManager.me().execute(AsyncFactory.doJob());
        System.out.println("===主线程运行完毕===");
    }
}
package com.szh.async;

import cn.hutool.core.date.StopWatch;
import org.omg.CORBA.TIMEOUT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        // 构造方法的参数表示的是StopWatch实例的id，标志着实例的身份
        StopWatch watch = new StopWatch("StopWatch-Learning");
        watch.start("test1");
        // 任务停止
        watch.stop();
        // 获取任务的名字
        String lastTaskName = watch.getLastTaskName();
        long task1TimeNanos = watch.getLastTaskTimeNanos();// 任务运行时间 纳秒
//        long lastTaskTimeMillis = watch.getLastTaskTimeMillis(); // 任务运行时间 微妙
        System.out.println("task1" + lastTaskName + "运行了" + task1TimeNanos + "ns");

        System.out.println("^^^^^^^^^^^^^^^^^^^^分割线^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        String info = watch.prettyPrint(TimeUnit.MILLISECONDS);
        System.out.println(info);
    }

}
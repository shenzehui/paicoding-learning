package com.szh.trace.test;


import org.junit.Test;

import java.util.Random;
import java.util.function.Supplier;

/**
 * 第一阶段的使用工具类
 */
public class Step1 {
    private static Random random = new Random();

    /**
     * 随机睡眠一段时间
     *
     * @param max
     */
    private static void randSleep(int max) {
        int sleepMillSecond = random.nextInt(max);
        try {
            System.out.println("随机休眠 " + sleepMillSecond + "ms");
            Thread.sleep(sleepMillSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCost() {
        long start = System.currentTimeMillis();
        randSleep(100);
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end - start) + "ms");
    }

    @Test
    public void testCost2() {
        CostUtil.cost(() -> {
            randSleep(100);
        });
    }

    @Test
    public void testCost3() {
        String ans = CostUtil.cost(() -> {
            randSleep(100);
            return "hello";
        });
        System.out.println("返回结果是：" + ans);
    }

    // 封装成工具类
    public static class CostUtil {
        /**
         * Runnable 无返回值
         *
         * @param run
         */
        public static void cost(Runnable run) {
            long start = System.currentTimeMillis();
            try {
                run.run();
            } finally {
                long end = System.currentTimeMillis();
                System.out.println("cost: " + (end - start) + "ms");
            }
        }

        /**
         * Supplier 有返回值
         *
         * @param sup
         * @param <T>
         * @return
         */
        public static <T> T cost(Supplier<T> sup) {
            long start = System.currentTimeMillis();
            try {
                return sup.get();
            } finally {
                long end = System.currentTimeMillis();
                System.out.println("cost: " + (end - start) + "ms");
            }
        }
    }

}
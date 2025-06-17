package com.szh;

import java.util.ArrayList;

public class EnsureCapacityTest {

    /**
     * 测试 ArrayList 的 ensureCapacity 方法，但是这点性能消耗可以忽略不计，且开发中我们也不会这么去写。
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<Object>();
        final int N = 10000000;
        long startTime = System.currentTimeMillis();
        // 理论上来说，最好在向 ArrayList 添加大量元素之前用 ensureCapacity 方法，以减少增量重新分配的次数
//        list.ensureCapacity(N);
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法前：" + (endTime - startTime));
//        System.out.println("使用ensureCapacity方法后：" +   (endTime - startTime));

    }
}
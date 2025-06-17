package com.szh;

import java.util.Arrays;

public class ArrayscopyOfTest {

    /**
     * 测试Arrays.copyOf()，个人感觉主要用于是给原数组扩容
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] a = new int[3];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        int[] b = Arrays.copyOf(a, 10);
        System.out.println("b.length" + b.length);
    }
}
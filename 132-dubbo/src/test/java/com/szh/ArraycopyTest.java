package com.szh;

public class ArraycopyTest {

    /**
     * 复制数组
     *
     * @param src     源数组
     * @param srcPos  源数组中的起始位置
     * @param dest    目标数组
     * @param destPos 目标数组中的起始位置
     * @param length  要复制的数组元素的数量
     * @param args
     */
    public static void main(String[] args) {
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        System.arraycopy(a, 2, a, 3, 3);
//        a[2] = 99;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
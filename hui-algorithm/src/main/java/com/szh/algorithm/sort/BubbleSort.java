package com.szh.algorithm.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public int[] bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) { // 一共进行 arr.length - 1 轮
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;

            // 每轮最后一个元素即为最大值
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // 本轮存在交换
                    flag = false;
                }
            }
            if (flag) {
                break;
            }

        }
        return arr;
    }

}

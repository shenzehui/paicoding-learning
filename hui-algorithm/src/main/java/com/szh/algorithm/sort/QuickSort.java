package com.szh.algorithm.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 快速排序
 * 时间复杂度 O(nlogn)
 */

public class QuickSort {

    public int[] sortArray(int[] a) {
        quick(a, 0, a.length - 1);
        return a;
    }


    public void quick(int[] a, int left, int right) {
        // 终止条件
        if (left >= right) {
            return;
        }
        int p = partition(a, left, right);
        quick(a, left, p - 1);
        quick(a, p + 1, right);

    }

    // 分区函数：将数组分为两部分，小于基准值的在左，大于基准值的在右
    int partition(int[] a, int left, int right) {
        // 随机选择一个基准点，避免最坏情况（如数组接近有序）
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(a, left, idx); // 将基准点放在数组的最左端
        int pv = a[left]; // 基准值
        int i = left + 1; // 左指针，指向当前需要检查的元素
        int j = right; // 右指针，从右往左寻找比基准值小的元素

        while (i <= j) {
            // 左指针向右移动，直到找到一个大于等于基准值的元素
            while (i <= j && a[i] < pv) {
                i++;
            }
            // 右指针向左移动，直到找到一个小于等于基准值的元素
            while (i <= j && a[j] > pv) {
                j--;
            }
            // 如果左指针尚未越过右指针，交换两个不符合位置的元素
            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }
        // 将基准值放到分区点位置，使得基准值左侧小于它，右侧大于它
        swap(a, j, left);
        return j;
    }

    // 交换数组中两个元素的位置
    void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.sortArray(new int[]{10, 7, 5, 4, 3, 1});
    }

}

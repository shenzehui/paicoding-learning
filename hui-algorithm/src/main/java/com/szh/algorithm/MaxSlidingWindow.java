package com.szh.algorithm;

import java.util.Deque;
import java.util.LinkedList;

public class MaxSlidingWindow {

    Deque<Integer> deque = new LinkedList<>();

    void pop(int val) {
        if (!deque.isEmpty() && deque.peekFirst() == val) {
            deque.pollFirst();
        }
    }


    void add(int val) {
        while (!deque.isEmpty() && val > deque.peekLast()) {
            deque.pollLast();
        }
        deque.addLast(val);
    }

    int peek() {
        return deque.peekFirst();
    }

    /**
     * 滑动窗口最大值（单调队列）
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        int length = nums.length;
        int[] res = new int[length - k + 1];

        int index = 0;
        // 将前K个元素加入到单调队列中
        for (int i = 0; i < k; i++) {
            add(nums[i]);
        }
        // 获取前K个元素中的最大值
        res[index++] = peek();

        // for循环，遍历数组，获取每个滑动窗口的最大值
        for (int i = k; i < nums.length; i++) {
            // 1.移除滑动窗口最左边的元素
            pop(nums[i - k]);
            // 2.添加滑动窗口最右边的元素
            add(nums[i]);
            // 3.记录滑动窗口的最大值
            res[index++] = peek();
        }
        return res;
    }

}

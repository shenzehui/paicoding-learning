package com.szh.algorithm.arrays;

import java.util.Arrays;

/**
 * 977. 有序数组的平方
 * <p>
 * 双指针法，数组两端的数组永远是最大的一个，所以从两端开始向中间移动，将平方后的值放入结果数组中
 */
public class SortedSquares {
    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int index = nums.length - 1;

        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[index--] = nums[left] * nums[left];
                left++;
            } else {
                result[index--] = nums[right] * nums[right];
                right--;
            }
        }

        // // 数组反转
        // for(int i =0 ;i<nums.length / 2;i++){
        //     int temp = result[i];
        //     result[i] = result[nums.length - 1 -i];
        //     result[nums.length - 1 -i] = temp;
        // }
        return result;
    }

    /**
     * 暴力解法
     */
    public int[] sortedSquares1(int[] nums) {
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] * nums[i];
        }

        Arrays.sort(result);

        return result;

    }

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        SortedSquares sortedSquares = new SortedSquares();
        int[] result = sortedSquares.sortedSquares(nums);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}

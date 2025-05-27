package com.szh.algorithm;

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

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        SortedSquares sortedSquares = new SortedSquares();
        int[] result = sortedSquares.sortedSquares(nums);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}

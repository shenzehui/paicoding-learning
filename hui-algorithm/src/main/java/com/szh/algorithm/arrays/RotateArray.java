package com.szh.algorithm.arrays;

/**
 * 旋转数组（向右轮转 k 个位置）
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        // 1.翻转整个数组
        reverse(nums, 0, nums.length - 1);
        // 2.翻转前 k 个元素
        reverse(nums, 0, k - 1);
        // 3.翻转从 k 到末尾的元素
        reverse(nums, k, nums.length - 1);
    }

    // todo 如果是向左旋转，步骤是：1.翻转前k个元素 2.翻转从k到末尾的元素 3.翻转整个数组

    public void reverse(int[] nums, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        new RotateArray().rotate(nums, 3);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

}

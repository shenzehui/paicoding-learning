package com.szh.algorithm.arrays;

/**
 * 移动零
 *
 * @author szh
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int slow = 0; // 数组下标

        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

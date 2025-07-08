package com.szh.algorithm.gredy;

/**
 * 55. 跳跃游戏
 */
public class JumpGame {

    int cover = 0;

    public boolean canJump(int[] nums) {
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, nums[i] + i);
            if (cover >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

}

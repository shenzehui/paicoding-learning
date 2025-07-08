package com.szh.algorithm.gredy;

/**
 * 45. 跳跃游戏 II
 */
public class JumpGameII {

    int cur = 0;
    int cover = 0;
    int result = 0;

    public int jump(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            cover = Math.max(cover, nums[i] + i);
            if (i == cur) {
                if (cur != nums.length - 1) {
                    result++;
                    cur = cover;
                    if (cur >= nums.length - 1) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return result;
    }
}

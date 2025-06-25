package com.szh.algorithm.dp;

/**
 * 213. 打家劫舍 II
 */
public class HouseRobbeII {

    public int rob(int[] nums) {
        // 剪枝
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        // 情况1：不偷第一间房间
        int result1 = robAction(nums, 1, nums.length - 1);
        // 情况2：不偷最后一间房间
        int result2 = robAction(nums, 0, nums.length - 2);
        // 取最大值
        return Math.max(result1, result2);
    }


    // 198.打家劫舍的逻辑
    public int robAction(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int[] dp = new int[nums.length];

        // 初始化
        dp[start] = nums[start];  // 第0个房间，必偷，最大值
        dp[start + 1] = Math.max(nums[start], nums[start + 1]); // 选择钱多的偷

        // 遍历 + 递推公式
        for (int i = start + 2; i < nums.length; i++) {
            // 不偷和偷的情况下选择最大值
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[end];
    }
}

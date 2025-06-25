package com.szh.algorithm.dp;

/**
 * 198. 打家劫舍
 */
public class HouseRobbe {

    public int rob(int[] nums) {

        // 只有一间房间
        if (nums.length == 1) {
            return nums[0];
        }

        // dp数组含义：考虑下标i(包含)，能偷到的最大金币数为dp[i]   result = dp[nums.size - 1]
        // 这里不一定偷取i这个房间
        int[] dp = new int[nums.length];

        // 初始化
        dp[0] = nums[0];  // 第0个房间，必偷，最大值
        dp[1] = Math.max(nums[0], nums[1]); // 选择钱多的偷

        // 遍历 + 递推公式
        for (int i = 2; i < nums.length; i++) {
            // 不偷和偷的情况下选择最大值
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }
}

package com.szh.algorithm.dp;

/**
 * 494. 目标和
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((target + sum) < 0 || (target + sum) % 2 == 1) {
            // 无解
            return 0;
        }

        int left = (target + sum) / 2;

        // dp数组含义：装满容量为j的背包有dp[j]种方法
        int[] dp = new int[left + 1];

        // 初始化
        dp[0] = 1;

        // 遍历 + 递推公式
        for (int i = 0; i < nums.length; i++) { // 物品
            for (int j = left; j >= nums[i]; j--) { // 背包容量
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}

package com.szh.algorithm.dp;

/**
 * 746.使用最小花费爬楼梯
 */
public class MinCostClimbingStairs {

    /**
     * 第一步不支付费用
     */
    public int minCostClimbingStairs(int[] cost) {
        // dp数组的含义：到达下标i的位置所需要的最小花费为dp[i]
        int[] dp = new int[cost.length + 1];
        // 初始化
        dp[0] = 0;
        dp[1] = 0;

        // 递推公式
        for (int i = 2; i < cost.length + 1; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[cost.length];
    }

    /**
     * 第一步支付费用
     */
    public int minCostClimbingStairs1(int[] cost) {
        // dp数组的含义：到达下标i的位置所需要的最小花费为dp[i]
        int[] dp = new int[cost.length];
        // 初始化
        dp[0] = cost[0];
        dp[1] = cost[1];

        // 递推公式
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }

        // 返回结果：一步或两步到达楼顶
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }
}

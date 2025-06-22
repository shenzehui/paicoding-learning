package com.szh.algorithm.dp;

/**
 * 爬楼梯
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        // dp数组含义：到达i阶有dp[i]种方法
        int[] dp = new int[n + 1];
        // 初始化
        dp[0] = 1;
        dp[1] = 1;
        // 递推公式
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

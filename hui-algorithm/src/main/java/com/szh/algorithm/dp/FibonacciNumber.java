package com.szh.algorithm.dp;

/**
 * 斐波那契数列
 *
 * @author szh
 */
public class FibonacciNumber {

    // 动态规划求解
    public int fib1(int n) {
        if (n < 1) {
            return n;
        }
        // 确认dp数组
        int[] dp = new int[n + 1];
        // 初始化
        dp[0] = 0;
        dp[1] = 1;
        // 确认递推公式，遍历顺序
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 递归求解
    public int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

}

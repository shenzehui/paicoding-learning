package com.szh.algorithm.dp;

/**
 * 343. 整数拆分
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        // dp数组含义：拆分整数i，得最大乘积是dp[i]
        int[] dp = new int[n + 1];

        // 初始化
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];

    }

}

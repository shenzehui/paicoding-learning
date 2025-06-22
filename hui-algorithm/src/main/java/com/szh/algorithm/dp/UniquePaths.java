package com.szh.algorithm.dp;

/**
 * 62.不同路径
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        // 递推数组含义：到达(i,j)位置有dp[i][j]种方法
        int[][] dp = new int[m][n];

        // 初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        // 递推公式 遍历赋值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];

    }
}

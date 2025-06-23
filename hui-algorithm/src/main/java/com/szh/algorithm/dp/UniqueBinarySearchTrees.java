package com.szh.algorithm.dp;

/**
 * 96. 不同的二叉搜索树
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 */
public class UniqueBinarySearchTrees {

    public int numTrees(int n) {

        // dp数组含义：n = i 的二叉搜索树数量是dp[i]
        int[] dp = new int[n + 1];

        // 初始化
        dp[0] = 1;

        // 状态转移方程 + 从前往后遍历
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // j 是根节点
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];

    }
}

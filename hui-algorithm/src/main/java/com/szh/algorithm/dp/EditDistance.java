package com.szh.algorithm.dp;

/**
 * 72. 编辑距离
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        // 定义 dp数组，以word1[i-1]为结尾的word1 和 以word2[j-1] 为结尾的word2 最少的操作次数为 dp[i][j]
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // 初始化
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        // 遍历 + 递推公式
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 相等，无需任务操作
                } else {
                    // word1 删除 or 新增 || word2 删除 or 新增 || 替换
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        int i = new EditDistance().minDistance("horse", "ros");
        System.out.println(i);
    }
}

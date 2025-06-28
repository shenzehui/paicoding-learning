package com.szh.algorithm.dp;

/**
 * 583. 两个字符串的删除操作
 */
public class DeleteOperationForTwoStrings {
    public int minDistance(String word1, String word2) {

        // dp数组含义：以word1[i-1] 为结尾的word1 和 以word2[j-1] 为结尾的 word2 相同的最小步数为 dp[i][j]
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
                    dp[i][j] = dp[i - 1][j - 1];  // 无需删除操作
                } else {
                    // 删除word1 || 删除word2 || word1 和 word2 都删 ，取最新小
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 2);
                }
            }
        }
        return dp[word1.length()][word2.length()];

    }

    public static void main(String[] args) {
        int i = new DeleteOperationForTwoStrings().minDistance("sea", "eat");
        System.out.println(i);
    }
}

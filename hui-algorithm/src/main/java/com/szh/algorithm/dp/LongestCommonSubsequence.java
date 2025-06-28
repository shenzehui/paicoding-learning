package com.szh.algorithm.dp;

/**
 * 1143. 最长公共子序列
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        // dp数组含义：以text1[i]和text2[j]结尾的最长公共子序列的长度是dp[i][j]
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        // 初始化


        // 遍历 + 递推公式  相等 dp[i][j] = dp[i-1][j-1] + 1  不相等 dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        int result = longestCommonSubsequence.longestCommonSubsequence("abcde", "ace");
    }
}
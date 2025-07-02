package com.szh.algorithm.dp;

/**
 * 516. 最长回文子序列
 */
public class LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String s) {

        int maxLength = 1;

        // dp数组含义：dp[i][j] = s[i...j]的最长回文子序列的长度
        int[][] dp = new int[s.length()][s.length()];

        // 初始化
//        for (int i = 0; i < s.length(); i++) {
//            dp[i][i] = 1;
//        }

        // 遍历顺序 + 递推公式
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i == j) {  // 相等，对角线，直接初始化0
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }

        return maxLength;

    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence longestPalindromicSubsequence = new LongestPalindromicSubsequence();
        int res = longestPalindromicSubsequence.longestPalindromeSubseq("bbbab");
        System.out.println(res);
    }

}

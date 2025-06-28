package com.szh.algorithm.dp;

/**
 * 115. 不同的子序列
 */
public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        // dp数组含义：以s[i-1]和t[j-1]结尾的最长子序列的长度是dp[i][j]
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        // 初始化
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= t.length(); j++) {
            dp[0][j] = 0;
        }
        dp[0][0] = 1;

        // 遍历 + 递推公式
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];   // 这里考虑使用 s 删除 s[i-1]，不考虑t删除的情况，因为t不变
                } else {
                    dp[i][j] = dp[i - 1][j];  // 这里因为不相等，所以直接使用 s[i-1]的情况
                }
            }
        }

        return dp[s.length()][t.length()];
    }

    public static void main(String[] args) {
        int i = new DistinctSubsequences().numDistinct("babgbag", "bag");
        System.out.println(i);
    }


}

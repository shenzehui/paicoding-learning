package com.szh.algorithm.dp;

/**
 * 392. 判断子序列
 */
public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        // dp数组含义：以s[i]和t[j]结尾的最长子序列的长度是dp[i][j]
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        // 遍历 + 递推公式
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

                    // 这里 s 字符串是不允许删除的，所以可以这样做。
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[s.length()][t.length()] == s.length();

    }


}

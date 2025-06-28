package com.szh.algorithm.dp;

/**
 * 647. 回文子串
 */
public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        // dp数组含义：dp数组含义：[i,j]范围内的字符串是否是回文子串
        boolean[][] dp = new boolean[s.length()][s.length()];

        int count = 0;

        // 初始化 dp[i][j] = false

        // 遍历 + 递推公式
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                        count++;
                    } else if (dp[i + 1][j - 1] == true) {
                        dp[i][j] = true;
                        count++;
                    }
                    // 其他情况都是 false
                }
                // 其他情况都是 false
            }
        }

        return count;
    }



    public static void main(String[] args) {
        PalindromicSubstrings palindromicSubstrings = new PalindromicSubstrings();
        System.out.println(palindromicSubstrings.countSubstrings("abc"));
    }
}

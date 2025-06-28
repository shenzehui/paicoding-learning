package com.szh.algorithm.dp;

/**
 * 5. 最长回文子串
 */
public class LongestPalindromicSubstring {


    /**
     * 回溯写法（超时）
     */
    public String longestPalindrome1(String s) {
        backTracking(s, 0);
        return res;
    }

    int maxLength = 0;

    String res = "";

    public void backTracking(String s, int startIndex) {
        if (startIndex >= s.length()) {
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            String str = s.substring(startIndex, i + 1);
            // 剪枝
            if (!isPalindrome(s, startIndex, i)) {
                continue;
            }

            if (str.length() > maxLength) {
                maxLength = str.length();
                res = str;
            }
            backTracking(s, i + 1);
        }
    }

    public boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 动规
     */
    public String longestPalindrome(String s) {
        // dp数组含义：[i,j]范围内的字符串是否是回文子串
        boolean[][] dp = new boolean[s.length()][s.length()];

        int maxLength = 0;

        String res = "";

        // 遍历 + 递推公式
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j] = true;

                    } else if (dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String res = longestPalindromicSubstring.longestPalindrome("babad");
        System.out.println(res);
    }

}

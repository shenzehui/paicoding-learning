package com.szh.algorithm.dp;

/**
 * 1035. 不相交的线
 */
public class UncrossedLines {

    // 这题与 LongestCommonSubsequence 最长公共子序列 一样!!
    public int maxUncrossedLines(int[] nums1, int[] nums2) {

        // dp数组含义：以nums1[i]和nums2[j]结尾的最长不相交子数组的长度是dp[i][j]
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];


        // 遍历 + 递推公式  相等 dp[i][j] = dp[i-1][j-1] + 1  不相等 dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i] == nums2[2]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[nums1.length][nums2.length];
    }
}

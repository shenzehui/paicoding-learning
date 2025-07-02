package com.szh.algorithm.dp;

/**
 * 718. 最长重复子数组
 */
public class MaximumLengthOfRepeatedSubarray {
    public int findLength(int[] nums1, int[] nums2) {

        // dp数组含义：以nums1[i]和nums2[j]结尾的最长重复子数组的长度是dp[i][j]
        int[][] dp = new int[nums1.length][nums2.length];

        // 初始化
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                dp[i][j] = nums1[i] == nums2[j] ? 1 : 0;
            }
        }

        int result = 0;


        // 递推公式：dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1] + 1)
        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }

        return result;

    }

    /**
     * 优化版本
     */
    public int findLength1(int[] nums1, int[] nums2) {
        int result = 0;

        // dp数组含义：以nums1[i-1]和nums2[j-1]结尾的最长重复子数组的长度是dp[i][j]
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }

        return result;
    }

    /**
     * todo 优化空间，使用滚动数组（一维）
     */
    public int findLength2(int[] nums1, int[] nums2) {
        return 0;
    }


    public static void main(String[] args) {
        MaximumLengthOfRepeatedSubarray maximumLengthOfRepeatedSubarray = new MaximumLengthOfRepeatedSubarray();
        int result = maximumLengthOfRepeatedSubarray.findLength1(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});
        System.out.println(result);
    }

}

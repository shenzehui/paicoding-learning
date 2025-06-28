package com.szh.algorithm.dp;

/**
 * 53. 最大子数组和
 */
public class MaximumSubarray {
    // 也可以用贪心来求解答
    public int maxSubArray(int[] nums) {
        int result = nums[0];

        // dp数组含义：dp[i]表示以nums[i]为结尾的最大子数组和
        int[] dp = new int[nums.length];

        // 初始化
        dp[0] = nums[0];

        // 遍历 + 递推公式 dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int i = new MaximumSubarray().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(i);
    }

}

package com.szh.algorithm.dp;

/**
 * 分割等和子集
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;

        // dp数组含义：容量为j的背包，最大价值是dp[j]
        int[] dp = new int[target + 1];

        // 初始化
        dp[0] = 0;

        // 状态转移方程 dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i]);
        for (int i = 0; i < nums.length; i++) {   // 物品
            for (int j = target; j >= nums[i]; j--) { // 背包
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }

            // 剪枝
            if (dp[target] == target) {
                return true;
            }
        }

        // 打印dp数组
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }

        return target == dp[target];

    }

    public static void main(String[] args) {
        System.out.println(new PartitionEqualSubsetSum().canPartition(new int[]{1, 5, 11, 5}));
    }
}

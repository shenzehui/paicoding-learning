package com.szh.algorithm.dp;

/**
 * 377. 组合总和 Ⅳ
 */
public class CombinationSum4 {

    public int combinationSum4(int[] nums, int target) {
        // dp数组含义，装满target容量的背包有dp[i]种方法
        int[] dp = new int[target + 1];

        // 初始化
        dp[0] = 1;

        // 遍历 + 递推公式   是排列问题（强调元素的顺序），参考零钱兑换II 是组合问题（不强调元素的顺序）
        for (int j = 0; j <= target; j++) { // 先遍历背包容量
            for (int i = 0; i < nums.length; i++) {  // 后遍历物品
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[target];
    }

}

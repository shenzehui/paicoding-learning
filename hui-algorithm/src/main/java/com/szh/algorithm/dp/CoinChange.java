package com.szh.algorithm.dp;

/**
 * 322. 零钱兑换
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        // dp数组含义：装满容量为j的背包最少需要dp[j]个物品
        int[] dp = new int[amount + 1];

        // 初始化
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        // 遍历 + 递推公式
        for (int i = 0; i < coins.length; i++) { // 遍历物品
            for (int j = coins[i]; j <= amount; j++) {  // 遍历背包容量
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {   // Integer.MAX_VALUE + 1 = -Integer.MAX_VALUE，所以后面的结果可能会被这个结果覆盖，导致出现问题
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        // 打印dp数组
        for (int e : dp) {
            System.out.println(e + " ");
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];

    }
}

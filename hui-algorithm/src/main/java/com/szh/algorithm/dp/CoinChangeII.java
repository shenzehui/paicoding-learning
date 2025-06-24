package com.szh.algorithm.dp;

/**
 * 518. 零钱兑换 II
 */
public class CoinChangeII {
    public int change(int amount, int[] coins) {
        // dp数组含义：装满容量为j的背包有dp[j]种方法
        int[] dp = new int[amount + 1];

        // 初始化
        dp[0] = 1;

        // 循环 + 递推公式  组合问题
        for (int i = 0; i < coins.length; i++) { // 遍历物品
            for (int j = coins[i]; j <= amount; j++) { // 遍历背包容量
                dp[j] += dp[j - coins[i]];
            }
        }

        // 排列问题
//        for (int j = 0; j <= amount; j++) { // 遍历背包容量
//            for (int i = 0; i < coins.length; i++) { // 遍历物品
//                if (j >= coins[i]) {
//                    dp[j] += dp[j - coins[i]];
//                }
//            }
//        }

        int index = 0;
        for (int res : dp) {
            System.out.println("背包容量为" + index++ + "有" + res + "种方法");
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChangeII coinChangeII = new CoinChangeII();
        int change = coinChangeII.change(5, new int[]{1, 2, 5});
        System.out.println("输出：" + change);
    }
}

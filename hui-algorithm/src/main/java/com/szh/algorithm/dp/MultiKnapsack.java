package com.szh.algorithm.dp;

import java.util.Scanner;

/**
 * 多重背包问题（了解即可）
 */
public class MultiKnapsack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /**
         * bagWeight:背包容量
         * n:物品种类
         */
        int bagWeight, n;

        //获取用户输入数据，中间用空格隔开，回车键换行
        bagWeight = sc.nextInt();
        n = sc.nextInt();
        int[] weight = new int[n];
        int[] value = new int[n];
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] dp = new int[bagWeight + 1];

        //先遍历物品再遍历背包，作为01背包处理
        for (int i = 0; i < n; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                //遍历每种物品的个数
                for (int k = 1; k <= nums[i] && (j - k * weight[i]) >= 0; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * value[i]);
                }
            }
        }
        System.out.println(dp[bagWeight]);
    }


}

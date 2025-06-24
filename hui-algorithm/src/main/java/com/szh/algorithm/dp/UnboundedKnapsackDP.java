package com.szh.algorithm.dp;

import java.util.Scanner;

/**
 * 完全背包问题
 */
public class UnboundedKnapsackDP {
//    /**
//     * 二维数组
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int bagWeight = scanner.nextInt();
//
//        int[] weight = new int[n];
//        int[] value = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            weight[i] = scanner.nextInt();
//            value[i] = scanner.nextInt();
//        }
//
//        int[][] dp = new int[n][bagWeight + 1];
//
//        // 初始化
//        for (int j = weight[0]; j <= bagWeight; j++) {
//            dp[0][j] = dp[0][j - weight[0]] + value[0];
//        }
//
//        // 动态规划
//        for (int i = 1; i < n; i++) {  // 遍历物品
//            for (int j = 0; j <= bagWeight; j++) {  // 遍历背包容量
//                if (j < weight[i]) {
//                    dp[i][j] = dp[i - 1][j];
//                } else {
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i]] + value[i]);
//                }
//            }
//        }
//
//        System.out.println(dp[n - 1][bagWeight]);
//        scanner.close();
//    }

    /**
     * 一维数组
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int bagWeight = scanner.nextInt();

        int[] weight = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            weight[i] = scanner.nextInt();
            value[i] = scanner.nextInt();
        }

        int[] dp = new int[bagWeight + 1];

        // 遍历 + 递推公式
        // 这里也可以先遍历背包，再遍历物品
        for (int i = 0; i < n; i++) {  // 遍历物品
            for (int j = weight[i]; j <= bagWeight; j++) {  // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        System.out.println(dp[bagWeight]);
        scanner.close();
    }
}

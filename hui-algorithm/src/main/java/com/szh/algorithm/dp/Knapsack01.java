package com.szh.algorithm.dp;

import java.util.Scanner;

/**
 * 01背包问题
 */
public class Knapsack01 {


    /**
     * 二维dp数组
     */
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int bagWeight = scanner.nextInt();
//
//        int[] weight = new int[n];
//        int[] value = new int[n];
//
//        for (int i = 0; i < n; ++i) {
//            weight[i] = scanner.nextInt();
//        }
//        for (int j = 0; j < n; ++j) {
//            value[j] = scanner.nextInt();
//        }
//
//        int[][] dp = new int[n][bagWeight + 1];
//
//        for (int j = weight[0]; j <= bagWeight; j++) {
//            dp[0][j] = value[0];
//        }
//
//        for (int i = 1; i < n; i++) {
//            for (int j = 0; j <= bagWeight; j++) {
//                if (j < weight[i]) {
//                    dp[i][j] = dp[i - 1][j];
//                } else {
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
//                }
//            }
//        }
//
//
//        System.out.println(dp[n - 1][bagWeight]);
//    }

    /**
     * 一维dp数组，滚动数组优化，推荐！
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取 M 和 N
        int M = scanner.nextInt();  // 物品数量
        int N = scanner.nextInt();  // 背包容量

        int[] costs = new int[M];   // 每种物品的空间占用
        int[] values = new int[M];  // 每种物品的价值

        // 输入每种物品的空间占用
        for (int i = 0; i < M; i++) {
            costs[i] = scanner.nextInt();
        }

        // 输入每种物品的价值
        for (int j = 0; j < M; j++) {
            values[j] = scanner.nextInt();
        }

        // 创建一个动态规划数组 dp，初始值为 0
        int[] dp = new int[N + 1];

        for (int i = 0; i < M; i++) {  // 遍历物品编号
            for (int j = N; j >= costs[i]; j--) { // 遍历背包容量
                // 考虑当前物品不选择和选择的情况，选择最大值
                dp[j] = Math.max(dp[j], dp[j - costs[i]] + values[i]);
            }
        }

        // 输出 dp[N]，即在给定 N 背包空间可以携带的物品的最大价值
        System.out.println(dp[N]);

        scanner.close();
    }

}

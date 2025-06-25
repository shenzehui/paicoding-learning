package com.szh.algorithm.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 279. 完全平方数
 */
public class PerfectSquares {

    // 思路：完全背包问题 + 组合
    public int numSquares(int n) {

        // 含义：装满容量为n的背包至少需要dp[n]个物品
        int[] dp = new int[n + 1];

        // 初始化
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
//
//        // 完全平方数集合
//        List<Integer> list = new ArrayList<>();
//        for (int i = 1; i <= n; i++) {
//            if (i * i <= n) {
//                list.add(i * i);
//            }
//        }
//
//        int[] nums = list.stream().mapToInt(Integer::intValue).toArray();
        // 遍历 + 递推公式
        for (int i = 0; i * i <= n; i++) {  // 遍历物品
            for (int j = i * i; j <= n; j++) { // 遍历背包容量
                if (dp[j - i * i] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }
        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }

    public static void main(String[] args) {
        PerfectSquares perfectSquares = new PerfectSquares();
        perfectSquares.numSquares(1);
    }

}

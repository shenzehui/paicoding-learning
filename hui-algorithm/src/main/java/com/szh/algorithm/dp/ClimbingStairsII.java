package com.szh.algorithm.dp;

import java.util.Scanner;

/**
 * //TODO 拓展题
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬至多m (1 <= m < n)个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 输入描述：输入共一行，包含两个正整数，分别表示n, m
 * <p>
 * 输出描述：输出一个整数，表示爬到楼顶的方法数。
 * <p>
 * 输入示例：3 2
 * <p>
 * 输出示例：3
 * <p>
 * 提示：
 * <p>
 * 当 m = 2，n = 3 时，n = 3 这表示一共有三个台阶，m = 2 代表你每次可以爬一个台阶或者两个台阶。
 * <p>
 * 此时你有三种方法可以爬到楼顶。
 * <p>
 * 1 阶 + 1 阶 + 1 阶段
 * 1 阶 + 2 阶
 * 2 阶 + 1 阶
 * <p>
 * 大致思路：
 * 如果一次爬的阶梯数不确定，这里我们认为是一个[1,m]的数组stairs，这题就可以用完全背包的思路求解，target 是 n（背包容量）
 * 递推公式dp[j] = dp[j-stairs[i]]  求排列数
 */

public class ClimbingStairsII {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m, n;
        while (sc.hasNextInt()) {
            // 从键盘输入参数，中间用空格隔开
            n = sc.nextInt();
            m = sc.nextInt();

            // dp数组含义：装满容量为n的背包，有dp[n]忠不同的方法，这里求排列问题
            int[] dp = new int[n + 1];
            dp[0] = 1;
            // 求排列问题，先遍历背包再遍历物品
            for (int j = 1; j <= n; j++) {
                for (int i = 1; i <= m; i++) {
                    if (j - i >= 0) {
                        dp[j] += dp[j - i];
                    }
                }
            }
            System.out.println(dp[n]);
        }
    }
}

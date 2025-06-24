package com.szh.algorithm.dp;

/**
 * 474. 一和零
 */
public class OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        // dp 数组含义：装满i个0 j个1 最多背dp[i][j]个物品，背包容量: i + j
        int[][] dp = new int[m + 1][n + 1];

        // 初始化
        dp[0][0] = 0;


        // 遍历 + 递推公式
        for (String str : strs) {  // 遍历物品
            int zeroNum = 0, oneNum = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }

            for (int i = m; i >= zeroNum; i--) {  // 遍历背包容量
                for (int j = n; j >= oneNum; j--) {
                    // dp[i-x][i-y] 表示不加这个物品的最大物品数  1 是加上当前物品
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        OnesAndZeroes onesAndZeroes = new OnesAndZeroes();
        int res = onesAndZeroes.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3);
        System.out.println("res = " + res);
    }

}

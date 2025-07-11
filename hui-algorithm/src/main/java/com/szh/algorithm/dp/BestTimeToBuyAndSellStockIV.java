package com.szh.algorithm.dp;

/**
 * 188. 买卖股票的最佳时机 IV
 */
public class BestTimeToBuyAndSellStockIV {


    /**
     * dp解法
     */
    public int maxProfitDP(int k, int[] prices) {
        /**
         * 递推公式含义：
         *
         * dp[i][0]  不操作
         * dp[i][1]  第一次持有（不是当前买入的意思，可能前面几天就买入了）
         * dp[i][2]  第一次不持有
         * dp[i][3]  第二次持有
         * dp[i][4]  第二次不持有
         *
         * ... 第k次不持有
         */
        int[][] dp = new int[prices.length][2 * k + 1];

        // 初始化
        for (int j = 1; j < 2 * k; j += 2) {
            dp[0][j] = -prices[0];
        }


        // 遍历 + 递推公式
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2 * k; j += 2) {
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }

        for (int[] ints : dp) {
            for (int i : ints) {
                System.out.print(i + ",");
            }
            System.out.println();
        }

        return dp[prices.length - 1][2 * k];
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIV time = new BestTimeToBuyAndSellStockIV();
        time.maxProfitDP(2, new int[]{3, 2, 6, 5, 0, 3});
    }

}

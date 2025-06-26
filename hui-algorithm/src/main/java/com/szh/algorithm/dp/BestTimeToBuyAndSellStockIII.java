package com.szh.algorithm.dp;

/**
 * 123. 买卖股票的最佳时机III
 */
public class BestTimeToBuyAndSellStockIII {


    /**
     * dp解法
     */
    public int maxProfitDP(int[] prices) {
        /**
         * 递推公式含义：
         *
         * dp[i][0]  不操作
         * dp[i][1]  第一次持有（不是当前买入的意思，可能前面几天就买入了）
         * dp[i][2]  第一次不持有
         * dp[i][3]  第二次持有
         * dp[i][4]  第二次不持有
         */
        int[][] dp = new int[prices.length][5];

        // 初始化
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;


        // 遍历 + 递推公式
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }

        // 返回第二次不持有（卖出状态），这里包含了第一次不持有的情况，所以就是结果
        return dp[prices.length - 1][4];
    }

}

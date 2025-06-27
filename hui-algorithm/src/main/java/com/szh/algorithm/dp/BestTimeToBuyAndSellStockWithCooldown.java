package com.szh.algorithm.dp;

/**
 * 309. 买卖股票的最佳时机含冷冻期.png
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {

        /**
         * todo 这题有点难理解
         * <p>
         * 卖出状态拆出来：保持卖出股票的状态、卖出股票的操作、冷冻期
         * dp数组含义：
         *     dp[i][0] 持有股票的状态
         *     dp[i][1] 保持卖出股票的状态
         *     dp[i][2] 卖出股票的操作        这里多加了这个状态，因为冷冻期需要由这个状态推导出来
         *     dp[i][3] 冷冻期
         */
        int[][] dp = new int[prices.length][4];


        // 初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;

        // 遍历 + 递推公式     [买入, 卖出, 冷冻期, 买入, 卖出]
        for (int i = 1; i < prices.length; i++) {
            // 昨天就持有股票了 或者 当天买入股票（两种：前一天是保持卖出股票状态或者冷冻期）  冷冻期之后的天数是保持卖出股票状态
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] - prices[i], dp[i - 1][3] - prices[i]));

            // 昨天就卖出股票了 或者 前天是冷冻期   这里的保持卖出股票状态，不包含当天卖出股票！！
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);

            // 当天卖出股票，所以前一天一定是持有股票状态
            dp[i][2] = dp[i - 1][0] + prices[i];

            // 冷冻期，肯定是前天是卖出股票操作
            dp[i][3] = dp[i - 1][2];
        }

        return Math.max(dp[prices.length - 1][1], Math.max(dp[prices.length - 1][2], dp[prices.length - 1][3]));

    }


}

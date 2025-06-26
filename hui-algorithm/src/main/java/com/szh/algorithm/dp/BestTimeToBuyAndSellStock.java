package com.szh.algorithm.dp;

/**
 * 121. 买卖股票的最佳时机
 */
public class BestTimeToBuyAndSellStock {

    /**
     * 贪心思路
     */
    public int maxProfit(int[] prices) {
        int low = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            // 贪数组左边买入最小
            low = Math.min(low, prices[i]);
            result = Math.max(prices[i] - low, result);
        }

        return result;
    }

    /**
     * dp解法
     */
    public int maxProfitDP(int[] prices) {
        // dp数组含义：第i天持有股票的最大金钱数为dp[i][0]  第i天不持有股票的最大金钱数为dp[i][1]
        int[][] dp = new int[prices.length][2];

        // 初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        // 遍历 + 递推公式
        for (int i = 1; i < prices.length; i++) {
            // 昨天就持有股票了 或者 当天买入股票
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            // 昨天就不持有股票 或者 当天卖出
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }

}

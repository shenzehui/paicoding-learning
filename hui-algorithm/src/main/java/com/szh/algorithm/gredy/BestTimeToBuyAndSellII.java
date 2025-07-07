package com.szh.algorithm.gredy;

/**
 * 122.买卖股票的最佳时机II
 */
public class BestTimeToBuyAndSellII {

    int result = 0;

    public int maxProfit(int[] prices) {
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        return result;
    }
}

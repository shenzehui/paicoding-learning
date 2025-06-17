package com.szh.algorithm;

/**
 * Title: 买卖股票的最佳时机 II
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
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

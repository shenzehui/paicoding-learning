package com.szh.algorithm.dp;

/**
 * 1049. 最后一块石头的重量 II
 */
public class LastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;

        for (int stone : stones) {
            sum += stone;
        }

        int target = sum / 2;

        // dp数组含义：容量为j的背包，最大价值是dp[j]
        int[] dp = new int[target + 1];

        // 初始化
        dp[0] = 0;

        // 遍历+ 递推公式
        for (int i = 0; i < stones.length; i++) {  // 物品
            for (int j = target; j >= stones[i]; j--) {  // 背包
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        // sum - dp[target] 一定比 dp[target] 大，因为sum/ 2 向下取整
        return sum - dp[target] - dp[target];

    }

}

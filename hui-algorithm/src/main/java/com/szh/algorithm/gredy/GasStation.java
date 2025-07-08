package com.szh.algorithm.gredy;

/**
 * 134. 加油站
 */
public class GasStation {

    int curSum = 0;
    int totalSum = 0;
    int start = 0;

    /**
     * 贪心策略
     * 思路：
     * 1. 遍历数组，计算当前位置的剩余油量
     * 2. 如果当前位置的剩余油量小于0，则将当前位置作为起点，并重新计算剩余油量
     * 3. 如果所有位置的剩余油量都大于0，则返回起点位置
     * 4. 如果所有位置的剩余油量都小于0，则返回-1
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {
                curSum = 0;
                start = (i + 1) % gas.length;
            }
        }
        // totalSum小于0，必然无法完成一圈，反之，start就是那个起点位置
        return totalSum < 0 ? -1 : start;
    }

    int minSum = 0;
    int sum = 0;


    /**
     * 贪心思路2
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            minSum = Math.min(minSum, sum);
        }

        if (sum < 0) {
            return -1;
        }
        if (minSum >= 0) {
            return 0;
        }

        for (int i = gas.length - 1; i > 0; i--) {
            minSum += gas[i] - cost[i];
            if (minSum >= 0) {
                return i;
            }
        }

        return -1;

    }
}

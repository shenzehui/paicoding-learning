package com.szh.algorithm.arrays;

/**
 * 209. 长度最小的子数组  滑动窗口（j表示终止位置，而i表示起始位置）
 */
public class MinSubArrayLen {

    public int minSubArrayLen(int target, int[] nums) {
        int i = 0; // 起始位置
        int sum = 0; // 子数组的和
        int subLength = Integer.MAX_VALUE;  // 子数组的长度
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target) {  // 注意这里是while
                subLength = Math.min(subLength, j - i + 1);
                sum -= nums[i];
                i++;
            }
        }
        // 如果数组加起来小于target，则返回0
        return subLength == Integer.MAX_VALUE ? 0 : subLength;
    }

}

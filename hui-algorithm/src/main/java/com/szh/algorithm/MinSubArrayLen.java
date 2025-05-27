package com.szh.algorithm;

public class MinSubArrayLen {

    /**
     * 209. 长度最小的子数组  滑动窗口（j表示终止位置，而i表示起始位置）
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int sum = 0;
        int subLength = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target) {
                subLength = Math.min(subLength, j - i + 1);
                sum -= nums[i];
                i++;
            }
        }
        return subLength == Integer.MAX_VALUE ? 0 : subLength;
    }

}

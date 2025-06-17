package com.szh.algorithm;

/**
 * 摆动序列
 */
public class WiggleSubsequence {

    int curDiff = 0; // 当前一对差值
    int preDiff = 0; // 前一对差值
    int result = 1;  // 记录峰值个数，序列默认序列最右边有一个峰值

    public int wiggleMaxLength(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            curDiff = nums[i + 1] - nums[i];
            // 出现峰值
            if ((preDiff >= 0 && curDiff < 0) || (preDiff <= 0 && curDiff > 0)) {
                result++;
                preDiff = curDiff; // 注意这里，只在摆动变化的时候更新prediff
            }
        }
        return result;
    }


}

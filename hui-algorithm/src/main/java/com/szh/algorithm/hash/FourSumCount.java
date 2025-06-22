package com.szh.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 四数相加 II
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 */
public class FourSumCount {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // key 是要前两个数组要查询的target，value是出现的次数
        Map<Integer, Integer> record = new HashMap<>();

        int count = 0;

        for (int value : nums1) {
            for (int k : nums2) {
                int sum = value + k;
                if (record.containsKey(sum)) {
                    record.put(sum, record.get(sum) + 1);
                } else {
                    record.put(sum, 1);
                }
            }
        }

        for (int k : nums3) {
            for (int i : nums4) {
                int sum = k + i;
                int target = -sum;
                if (record.containsKey(target)) {
                    count += record.get(target);
                }
            }
        }
        return count;
    }
}

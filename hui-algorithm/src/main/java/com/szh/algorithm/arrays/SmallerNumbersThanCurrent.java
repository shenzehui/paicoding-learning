package com.szh.algorithm.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1365. 有多少小于当前数字的数字
 */
public class SmallerNumbersThanCurrent {

    public int[] smallerNumbersThanCurrent(int[] nums) {

        // k = 数字 v=索引下标
        Map<Integer, Integer> map = new HashMap<>();

        int[] res = Arrays.copyOf(nums, nums.length);
        Arrays.sort(res);

        for (int i = 0; i < res.length; i++) {
            if (!map.containsKey(res[i])) {
                map.put(res[i], i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            res[i] = map.get(nums[i]);
        }

        return res;

    }
}

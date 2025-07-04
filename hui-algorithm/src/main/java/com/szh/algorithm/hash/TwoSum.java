package com.szh.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * @author szh
 **/
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];   // 遍历当前元素，并在map中寻找是否有匹配的key
            if (map.containsKey(temp)) {
                res[1] = i;
                res[0] = map.get(temp);
                break;
            }
            map.put(nums[i], i);    // 如果没找到匹配对，就把访问过的元素和下标加入到map中
        }
        return res;

    }
}

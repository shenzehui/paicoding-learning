package com.szh.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90.子集II
 */
public class SubsetsII {

    List<Integer> path = new ArrayList<>();

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backTracking(nums, 0);
        return result;
    }

    public void backTracking(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));
        if (nums.length == startIndex) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            int num = nums[i];
            // 树层去重
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(num);
            backTracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        SubsetsII subsetsII = new SubsetsII();
        List<List<Integer>> result = subsetsII.subsetsWithDup(nums);
        System.out.println(result);
    }
}
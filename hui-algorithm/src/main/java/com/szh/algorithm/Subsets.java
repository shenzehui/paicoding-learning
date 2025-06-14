package com.szh.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 */
public class Subsets {

    List<Integer> path = new ArrayList<>();

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
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
            path.add(num);
            backTracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Subsets subsets = new Subsets();
        List<List<Integer>> result = subsets.subsets(nums);
        System.out.println(result);
    }
}

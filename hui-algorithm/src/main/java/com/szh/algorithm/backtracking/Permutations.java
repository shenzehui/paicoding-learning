package com.szh.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 46.全排列
 */
public class Permutations {

    List<Integer> path = new ArrayList<>();

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        backTracking(nums, new int[nums.length]);
        return result;
    }

    public void backTracking(int[] nums, int[] used) {
        if (nums.length == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) {
                continue;
            }
            used[i] = 1;
            path.add(nums[i]);
            backTracking(nums, used);
            path.remove(path.size() - 1);
            used[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permutations permutations = new Permutations();
        List<List<Integer>> result = permutations.permute(nums);
        System.out.println(result);
    }
}

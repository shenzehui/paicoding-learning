package com.szh.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 47.全排列 II
 */
public class PermutationsII {

    List<Integer> path = new ArrayList<>();

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        backTracking(nums, new int[nums.length]);
        return result;
    }

    public void backTracking(int[] nums, int[] used) {
        if (nums.length == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }

//        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            // 树枝去重
//            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 1) {
//                continue;
//            }
            // 树层去重
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) {
                continue;
            }
            if (used[i] == 1) {
                continue;
            }
//            set.add(nums[i]);
            used[i] = 1;
            path.add(nums[i]);
            backTracking(nums, used);
            path.remove(path.size() - 1);
            used[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        PermutationsII permutationsII = new PermutationsII();
        List<List<Integer>> result = permutationsII.permuteUnique(nums);
        System.out.println(result);
    }

}

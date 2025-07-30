package com.szh.algorithm.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 491. 递增子序列
 */
public class FindSubsequences {

    List<Integer> path = new ArrayList<>();

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backTracking(nums, 0);
        return result;
    }

    public void backTracking(int[] nums, int startIndex) {
        if (path.size() > 1) {
            result.add(new ArrayList<>(path));
        }
        if (startIndex >= nums.length) {
            return;
        }

        Set<Integer> set = new HashSet<>();

        for (int i = startIndex; i < nums.length; i++) {
            int num = nums[i];
            if ((!path.isEmpty() && nums[i] < path.get(path.size() - 1)) || set.contains(num)) {
                continue;
            }
            set.add(num);
            path.add(num);
            backTracking(nums, i + 1);
            path.remove(path.size() - 1);
            // 注意set不需要回溯，因为set是每一重递归的重复元素
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new FindSubsequences().findSubsequences(new int[]{4, 6, 7, 7});
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer);
            }
            System.out.println();
        }
    }

}
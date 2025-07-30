package com.szh.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 */
public class CombinationSum2 {

    List<Integer> list = new ArrayList<>();

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracking(candidates, target, 0);
        return res;
    }

    public void backTracking(int[] candidates, int target, int startIndex) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            // 数层去重逻辑，不代表结果集中不存在重复元素
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);

            backTracking(candidates, target - candidates[i], i + 1);

            // 回溯
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum2 combinationSum2 = new CombinationSum2();
        List<List<Integer>> lists = combinationSum2.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        // 1,1,2,5,6,7,10
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer);
            }
            System.out.println();
        }
    }
}

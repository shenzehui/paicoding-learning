package com.szh.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 */
public class CombinationSum {


    List<Integer> list = new ArrayList<>();

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTracking(candidates, target, 0);
        return res;
    }

    public void backTracking(int[] candidates, int target, int startIndex) {
        if (target <= 0) {
            if (target == 0) {
                res.add(new ArrayList<>(list));
            }
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            list.add(candidates[i]);
            // 注意这里不是 i + 1
            backTracking(candidates, target - candidates[i], i);

            // 回溯
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> lists = combinationSum.combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer);
            }
            System.out.println();
        }
    }
}

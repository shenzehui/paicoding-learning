package com.szh.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 */
public class CombinationSum3 {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTracking(k, n, 1);
        return res;
    }

    /**
     * 递归回溯
     *
     * @param k          组合的个数
     * @param target     目标值
     * @param startIndex 开始遍历的索引
     */
    public void backTracking(int k, int target, int startIndex) {
        if (path.size() == k) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = startIndex; i <= 9; i++) {
            path.add(i);
            backTracking(k, target - i, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum3 combinationSum3 = new CombinationSum3();
        List<List<Integer>> lists = combinationSum3.combinationSum3(3, 7);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer);
            }
            System.out.println();
        }
    }
}

package com.szh.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 */
public class Combinations {

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backTracking(n, k, 1);
        return res;
    }

    public void backTracking(int n, int k, int startIndex) {  //startIndex，是每次递归的起始位置
        // 终止条件
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 单层递归过程
        // 这里剪枝操作说明，如果剩余的数字的个数不足k-path.size()，则没有必要继续递归了
//        for (int i = startIndex; i <= n; i++) {
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            // 递归，其实这里就是循环里套循环，如果k = 2,就是双重for循环。
            backTracking(n, k, i + 1);
            // 回溯
            path.remove(path.size() - 1);

        }
    }
}

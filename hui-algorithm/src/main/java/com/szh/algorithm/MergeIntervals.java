package com.szh.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: 合并区间
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();

        int start = 0;
        int end = intervals[0][1];

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 1; i < intervals.length; i++) {
            if (end < intervals[i][0]) {
                // 不重合情况下
                int[] temp = new int[]{start, end};
                res.add(temp);
                // 去更新左右边界
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                // 这里只需要去更新维护右边界即可。取最大值是因为前数组的右边界可能比当前数组的右边界大！！
                end = Math.max(intervals[i][1], end);
            }
        }

        res.add(new int[]{start, end});

        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {

    }

}

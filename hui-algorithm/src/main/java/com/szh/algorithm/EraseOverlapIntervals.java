package com.szh.algorithm;

import java.util.Arrays;

/**
 * Title:无重叠区间
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class EraseOverlapIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {

        int result = 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1][1] <= intervals[i][0]) {
                continue;
            } else {
                // 这里i区间删除，更新右边界其实就是模拟删除（逻辑删除）
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
                result++;
            }
        }
        return result;
    }
}

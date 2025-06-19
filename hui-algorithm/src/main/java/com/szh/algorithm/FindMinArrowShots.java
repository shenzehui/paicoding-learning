package com.szh.algorithm;

import java.util.Arrays;

/**
 * Title: 用最少数量的箭引爆气球
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class FindMinArrowShots {
    public int findMinArrowShots(int[][] points) {

        // 根据气球直径的开始坐标从小到大排序
        // 使用Integer内置比较方法，不会溢出
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        int count = 1;// points 不为空只少需要一支箭

        for (int i = 1; i < points.length; i++) {
            if (points[i - 1][1] < points[i][0]) {
                count++;
            } else {
                // 更新重叠气球最小右边界(i的右边界)
                points[i][0] = Math.min(points[i - 1][1], points[i][1]);
            }
        }

        return count;

    }


}

package com.szh.algorithm;

/**
 * 135. 分发糖果
 * <p>
 * 这道题目一定是要确定一边之后，再确定另一边，例如比较每一个孩子的左边，然后再比较右边，如果两边一起考虑一定会顾此失彼。
 * <p>
 * 这种思路可以以后可以参考下
 */
public class Candy {
    public int candy(int[] ratings) {

        int[] left = new int[ratings.length];

        // 先确定右边评分大于左边的情况（也就是从前向后遍历）
        for (int i = 0; i < ratings.length; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        int[] right = new int[ratings.length];

        // 再确定右边评分小于左边的情况（也就是从后向前遍历）
        for (int i = ratings.length - 1; i >= 0; i--) {
            if (i < ratings.length - 1 && ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        // 确定最终的糖果数
        int sum = 0;
        for (int i = 0; i < ratings.length; i++) {
            // 取左右两边的糖果数中较大的
            sum += Math.max(left[i], right[i]);
        }
        return sum;

    }

}

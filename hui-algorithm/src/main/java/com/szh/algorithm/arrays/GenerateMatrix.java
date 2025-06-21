package com.szh.algorithm.arrays;

/**
 * 螺旋矩阵 II
 *
 * @author szh
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int startX = 0;
        int startY = 0;  // 每一圈的起始位置
        int offset = 1;  // 每一圈的偏移量
        int[][] nums = new int[n][n];
        int i;
        int j;
        int count = 1; // 填充数字
        for (int k = 0; k < n / 2; k++) {
            // 左闭右开填充
            for (j = startY; j < n - offset; j++) {
                nums[startX][j] = count++;
            }
            // 上闭下开填充
            for (i = startX; i < n - offset; i++) {
                nums[i][j] = count++;
            }
            // 右闭左开填充
            for (; j > startY; j--) {
                nums[i][j] = count++;
            }
            // 下闭上开填充
            for (; i > startX; i--) {
                nums[i][j] = count++;
            }
            startX++;
            startY++;
            offset++;
        }
        // 奇数，填充中间的数字
        if (n % 2 == 1) {
            nums[startX][startY] = count;
        }
        return nums;
    }
}

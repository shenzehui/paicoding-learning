package com.szh.algorithm.arrays;

/**
 * 寻找数组的中心索引
 *
 * @author szh
 */
public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < nums.length; i++) {
            leftSum += nums[i];
            rightSum = sum - leftSum + nums[i];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindPivotIndex findPivotIndex = new FindPivotIndex();
        System.out.println(findPivotIndex.pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
        System.out.println(findPivotIndex.pivotIndex(new int[]{1, 2, 3}));
    }

}

package com.szh.algorithm.gredy;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 1005. K 次取反后最大化的数组和
 */
public class LargestSumAfterKNegations {

    public int largestSumAfterKNegations(int[] nums, int k) {
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] *= -1;
                k--;
            }
        }
        if (k % 2 == 1) {
            nums[nums.length - 1] *= -1;
        }

        return Arrays.stream(nums).sum();

    }
}

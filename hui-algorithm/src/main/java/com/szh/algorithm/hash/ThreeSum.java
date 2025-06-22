package com.szh.algorithm.hash;

import java.util.*;

/**
 * 三数之和
 */
public class ThreeSum {

    /**
     * 双指针法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 如果i大于0，直接返回即可
            if (nums[i] > 0) {
                return res;
            }
            // i 所在位置去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {  // 如果这里是 <= 那么就是两数之和了，不符合规则
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);

                    // 发现当前节点跟后面一个节点相等，左指针后移
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 发现当前节点跟前面一个节点相等，右指针前移
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                }
            }

        }

        return res;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] temp = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum.threeSum(temp);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + ",");
            }
            System.out.println();
        }
    }


}

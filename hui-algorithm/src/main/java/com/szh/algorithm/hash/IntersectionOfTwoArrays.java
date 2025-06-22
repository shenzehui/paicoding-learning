package com.szh.algorithm.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 两个数组的交集
 */
public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();

        for (int j : nums1) {
            set.add(j);
        }

        for (int j : nums2) {
            if (set.contains(j)) {
                res.add(j);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();

    }

    public int[] intersection1(int[] nums1, int[] nums2) {

        int[] record = new int[1004];

        Set<Integer> res = new HashSet<>();

        for (int j : nums1) {
            record[j] = 1;
        }

        for (int j : nums2) {
            if (record[j] == 1) {
                res.add(j);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();

    }

    public static void main(String[] args) {
        IntersectionOfTwoArrays intersectionOfTwoArrays = new IntersectionOfTwoArrays();
        int[] intersection = intersectionOfTwoArrays.intersection1(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        for (int i : intersection) {
            System.out.print(i);
        }
    }
}

package com.szh.algorithm.gredy;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. 划分字母区间
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {

        int[] hash = new int[27];

        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] = i;
        }

        int left = 0;
        int right = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            right = Math.max(right, hash[s.charAt(i) - 'a']);
            if (right == i) {
                res.add(right - left + 1);
                left = i + 1;
            }
        }
        return res;
    }

}

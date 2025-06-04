package com.szh.algorithm;

public class RepeatedSubstringPattern {

    /**
     * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
     * <p>
     * 示例 1:
     * 输入: s = "abab"
     * 输出: true
     * 解释: 可由子串 "ab" 重复两次构成。
     * <p>
     * 示例 2:
     * 输入: s = "aba"
     * 输出: false
     * <p>
     * 示例 3:
     * 输入: s = "abcabcabcabc"
     */
    public boolean repeatedSubstringPattern(String s) {
        if (s.isEmpty()) {
            return false;
        }
        int n = s.length();

        // Step 1.构建KMP算法的前缀表
        int[] next = new int[n]; // 前缀表的值表示 以该位置结尾的字符串的最长相等前后缀的长度
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                // 只要前缀后缀还不一致，就根据前缀表回退j直到起点为止
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        // Step 2.判断重复子字符串
        if (next[n - 1] > 0 && n % (n - next[n - 1]) == 0) { // 当字符串s的长度可以被其最长相等前后缀不包含的子串的长度整除时
            return true; // 不包含的子串就是s的最小重复子串
        } else {
            return false;
        }
    }
}

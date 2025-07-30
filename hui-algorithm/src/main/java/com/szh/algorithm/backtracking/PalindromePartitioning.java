package com.szh.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 */
public class PalindromePartitioning {

    List<List<String>> res = new ArrayList<>();

    List<String> path = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backTracking(s, 0);
        return res;

    }

    public void backTracking(String s, int startIndex) {
        if (startIndex >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            String str = s.substring(startIndex, i + 1);
            // 剪枝
            if (!isPalindrome(s, startIndex, i)) {
                continue;
            }
            path.add(str);
            backTracking(s, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
        List<List<String>> lists = palindromePartitioning.partition("aab");
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.print(s);
            }
            System.out.println();
        }
    }
}

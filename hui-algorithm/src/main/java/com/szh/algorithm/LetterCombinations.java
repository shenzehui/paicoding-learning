package com.szh.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合
 *
 * @author szh
 */
public class LetterCombinations {

    // 输入：digits = "23"
    String[] letters = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        backTracking(digits, 0);
        return res;

    }

    String s;
    List<String> res = new ArrayList<>();

    /**
     * 回溯
     *
     * @param digits
     * @param index  输入的下标,也就是递归的层数
     */
    private void backTracking(String digits, int index) {
        if (index == digits.length()) {
            res.add(s);
            return;
        }

        int digit = Integer.parseInt(String.valueOf(digits.charAt(index)));
        String letters = this.letters[digit];

        for (int i = 0; i < letters.length(); i++) {
            s += letters.charAt(i);
            // todo 这里可以多加一个参数,隐藏回溯
            // backTracking(digits, index + 1,s += letters.charAt(i));
            backTracking(digits, index + 1);
            s = s.substring(0, s.length() - 1);
        }
    }

}

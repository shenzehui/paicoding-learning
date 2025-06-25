package com.szh.algorithm.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 139. 单词拆分
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {

        // dp数组含义：字符串长度为i的字符串是否可以被拼接dp[i]（true，false）
        boolean[] dp = new boolean[s.length() + 1];

        // 初始化
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = false;
        }

        // 遍历 + 递推公式  排列问题
        for (int i = 1; i <= s.length(); i++) { // 背包
            for (int j = 0; j < i; j++) { // 物品
                String substring = s.substring(j, i);
                if (wordDict.contains(substring) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        ArrayList<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        boolean res = wordBreak.wordBreak("leetcode", wordDict);
        System.out.println(res);
    }
}

package com.szh.algorithm.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * 1002. 查找共用字符
 */
public class CommonChars {
    /**
     * 思路：
     * 1. 创建一个26长度的数组，记录第一个单词中每个字母出现的次数
     * 2. 遍历单词数组，将每个单词中字母出现的次数与第一个单词中字母出现的次数进行比较，取最小值
     * 3. 创建一个结果数组，将最小值添加到结果数组中
     * 4. 返回结果数组
     *
     * @param words
     * @return
     */
    public List<String> commonChars(String[] words) {
        List<String> res = new ArrayList<>();
        int[] record = new int[26];

        for (int i = 0; i < words[0].length(); i++) {
            String str = words[0];
            record[str.charAt(i) - 'a']++;
        }

        for (int i = 1; i < words.length; i++) {
            int[] temp = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                temp[words[i].charAt(j) - 'a']++;
            }

            for (int k = 0; k < 26; k++) {
                record[k] = Math.min(record[k], temp[k]);
            }
        }

        for (int i = 0; i < 26; i++) {
            while (record[i] > 0) {
                char c = (char) (i + 'a');
                res.add(String.valueOf(c));
                record[i]--;
            }
        }

        return res;
    }
}

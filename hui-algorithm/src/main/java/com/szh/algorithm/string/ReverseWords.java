package com.szh.algorithm.string;

/**
 * 翻转字符串里的单词
 *
 * @author szh
 */
public class ReverseWords {

    public String reverseWords(String s) {
        // 1.去除首尾以及中间多余空格
        String sb = removeSpace(s);
        // 2.反转整个字符串
        char[] ch = sb.toCharArray();
        reverseString(ch, 0, ch.length);
        // 3.反转各个单词
        reverseEachWord(ch);
        return new String(ch);
    }

    /**
     * 移除空格
     *
     * @param s
     * @return
     */
    public String removeSpace(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (s.charAt(left) == ' ') {
            left++;
        }
        while (s.charAt(right) == ' ') {
            right--;
        }
        s = s.substring(left, right + 1);
        StringBuilder res = new StringBuilder();
        left = 0;
        right = s.length() - 1;
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                if (left > 0 && s.charAt(left - 1) == ' ') {
                    res.append(" ");
                }
                res.append(c);
            }
            left++;
        }
        return res.toString();
    }

    /**
     * 反转字符串
     *
     * @param ch
     * @param start
     * @param end
     */
    public void reverseString(char[] ch, int start, int end) {
        for (int i = start, j = end - 1; i < (start + end) / 2; i++, j--) {
            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
        }
    }

    /**
     * 反转每个单词
     *
     * @param ch
     */
    private void reverseEachWord(char[] ch) {
        int start = 0;
        int end = 1;

        int n = ch.length;

        while (start < n) {
            while (end < n && ch[end] != ' ') {
                end++;
            }
            // end 取的是空格位置
            reverseString(ch, start, end);
            start = end + 1;
            end = start + 1;
        }
    }

    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        String s = reverseWords.reverseWords("  hello world  ");
        System.out.println(s);
    }
}

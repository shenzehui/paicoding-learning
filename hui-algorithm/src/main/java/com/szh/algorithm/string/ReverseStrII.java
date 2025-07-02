package com.szh.algorithm.string;

/**
 * 反转字符串II
 */
public class ReverseStrII {

    public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i += 2 * k) {
            int start = i;
            int end = Math.min(ch.length - 1, i + k - 1);

            while (start < end) {
                char temp = ch[start];
                ch[start] = ch[end];
                ch[end] = temp;
                start++;
                end--;
            }
        }
        return new String(ch);
    }
}

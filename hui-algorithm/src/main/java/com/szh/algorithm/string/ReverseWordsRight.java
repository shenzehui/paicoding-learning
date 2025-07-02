package com.szh.algorithm.string;

/**
 * 右旋字符串
 */
public class ReverseWordsRight {

    public String reverseWordsRight(String s, int k) {
        char[] ch = s.toCharArray();
        // 1.翻转整个字符串   左闭右开
        reverseString(ch, 0, ch.length);
        // 2.翻转前K个字符串
        reverseString(ch, 0, k);
        // 3.翻转后k个字符穿
        reverseString(ch, k, ch.length);
        return new String(ch);
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

    public static void main(String[] args) {
        ReverseWordsRight reverseWordsRight = new ReverseWordsRight();
        String res = reverseWordsRight.reverseWordsRight("abcdefg", 2);
        System.out.println(res);
    }

}

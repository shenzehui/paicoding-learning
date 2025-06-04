package com.szh.algorithm;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class FindTheIndexOfTheFirst {

    /**
     * 获取next数组
     *
     * @param next
     * @param s
     */
    public void getNext(int[] next, String s) {
        // 说明：j是next数组的值，默认-1（做了统一减1的操作） i是字符串的索引(赋值的位置)
        int j = -1;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) { // 注意从1开始
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) { // 前后缀不相同
                j = next[j]; // J向前回退
            }
            if (s.charAt(i) == s.charAt(j + 1)) { // 前后缀相同
                j++;
            }
            next[i] = j; // 更新next[i]
        }
    }

    /**
     * KMP算法
     *
     * @param haystack 文本串
     * @param needle   模式串
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        // next数组
        int[] next = new int[needle.length()];
        getNext(next, needle);

        int j = -1; // 模式串的索引
        for (int i = 0; i < haystack.length(); i++) {
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];  // j等于前一位的next[j]
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            if (j == needle.length() - 1) {
                return (i - needle.length() + 1);
            }
        }
        return -1;
    }


    /**
     * 第二个版本，没有减一操作，更加清晰客观一点
     *
     * @param next
     * @param s
     */
    public void getNext1(int[] next, String s) {
        // 说明：j是next数组的值，默认-1（做了统一减1的操作） i是字符串的索引(赋值的位置)
        int j = 0;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) { // 注意从1开始
            while (j > 0 && s.charAt(i) != s.charAt(j)) { // 前后缀不相同
                j = next[j - 1]; // J向前回退
            }
            if (s.charAt(i) == s.charAt(j)) { // 前后缀相同
                j++;
            }
            next[i] = j; // 更新next[i]
        }
    }

    public int strStr1(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        // next数组
        int[] next = new int[needle.length()];
        getNext1(next, needle);

        int j = 0; // 模式串的索引
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];  // j等于前一位的next[j]
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length()) {
                return (i - needle.length() + 1);
            }
        }
        return -1;
    }
}

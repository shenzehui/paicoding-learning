package com.szh.algorithm;

/**
 * Title: 单调递增的数字
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class MonotoneIncreasingDigits {

    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int start = s.length(); // 样例本身符合要求，就赋值成s.length
        for (int i = s.length() - 1; i > 0; i--) {
            if (chars[i - 1] > chars[i]) {
                chars[i - 1]--;
                start = i;
            }
        }
        for (int i = start; i < s.length(); i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    public static void main(String[] args) {
        char n = '1';
        n--;
        System.out.println(n);

        // 100 -> 000 -> string 099 -> int 99
    }

}

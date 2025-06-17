package com.szh.algorithm;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

import java.util.Arrays;

/**
 * 分发饼干
 */
public class AssignCookies {

    private int count = 0;

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = s.length - 1;
        for (int i = g.length - 1; i >= 0; i++) {
            if (index >= 0 && s[index] >= g[i]) {
                index--;
                count++;
                i--;
            }
        }
        return count;
    }

}

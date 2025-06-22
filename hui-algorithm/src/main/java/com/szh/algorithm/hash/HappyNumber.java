package com.szh.algorithm.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 快乐数
 *
 * @author szh
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int temp = n;
        while (temp != 1 && !set.contains(temp)) {
            set.add(temp);
            temp = getNumber(temp);
        }
        // temp 要么等于1，要么就是循环的数
        return temp == 1;
    }


    public int getNumber(int n) {
        int res = 0;

        while (n != 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }
}

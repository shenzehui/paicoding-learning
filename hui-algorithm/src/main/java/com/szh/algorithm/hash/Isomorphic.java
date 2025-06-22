package com.szh.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 同构字符串
 */
public class Isomorphic {

    /**
     * 输入: s = "egg", t = "add"
     * 输出: true
     * 输入: s = "foo", t = "bar"
     * 输出: false
     * 输入: s = "paper", t = "title"
     * 输出: true
     * <p>
     * 思路：
     * 创建两个map，一个记录s->t，一个记录t->s
     * 遍历s，判断t，遍历t，判断s
     *
     */
    public boolean isIsomorphic(String s, String t) {

        Map<Character, Character> record1 = new HashMap<>();
        Map<Character, Character> record2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            if (!record1.containsKey(s.charAt(i))) {
                record1.put(s.charAt(i), t.charAt(i));
            }

            if (!record2.containsKey(t.charAt(i))) {
                record2.put(t.charAt(i), s.charAt(i));
            }

            if (record1.get(s.charAt(i)) != t.charAt(i) || record2.get(t.charAt(i)) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean isomorphic = new Isomorphic().isIsomorphic("egg", "add");
        System.out.println(isomorphic);

    }
}

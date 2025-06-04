package com.szh.algorithm;

import java.util.Stack;

public class ValidParentheses {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "()"
     * 输出：true
     * <p>
     **/
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char str = s.charAt(i);
            if (str == '(') {
                stack.push(')');
            } else if (str == '{') {
                stack.push('}');
            } else if (str == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char c = stack.peek();
                if (c == str) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}

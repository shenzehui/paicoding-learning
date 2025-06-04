package com.szh.algorithm;

import java.util.Stack;

public class EvalRPN {


    /**
     * 逆波兰表达式
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (token.equals("+")) {
                String num2 = stack.pop();
                String num1 = stack.pop();
                stack.push(String.valueOf(Integer.parseInt(num1) + Integer.parseInt(num2)));
            } else if (token.equals("-")) {
                String num2 = stack.pop();
                String num1 = stack.pop();
                stack.push(String.valueOf(Integer.parseInt(num1) - Integer.parseInt(num2)));
            } else if (token.equals("*")) {
                String num2 = stack.pop();
                String num1 = stack.pop();
                stack.push(String.valueOf(Integer.parseInt(num1) * Integer.parseInt(num2)));
            } else if (token.equals("/")) {
                String num2 = stack.pop();
                String num1 = stack.pop();
                stack.push(String.valueOf(Integer.parseInt(num1) / Integer.parseInt(num2)));
            } else {
                // 数字
                stack.push(token);
            }
        }
        return stack.peek() == null ? 0 : Integer.parseInt(stack.pop());
    }
}

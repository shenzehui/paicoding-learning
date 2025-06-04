package com.szh.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单个队列实现栈
 *
 * @author szh
 */
public class MyStack {

    Deque<Integer> queue;

    public MyStack() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {
        // 放到最后
        queue.addLast(x);
    }

    public int pop() {
        int size = queue.size();
        size--;
        while (size-- > 0) {
            queue.offer(queue.peekFirst());
            queue.pollFirst();
        }
        // 弹出第一个元素
        return queue.pollFirst();

    }

    public int top() {
        return queue.peekLast();

    }

    public boolean empty() {
        return queue.isEmpty();
    }


}

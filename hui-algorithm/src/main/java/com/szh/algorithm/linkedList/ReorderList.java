package com.szh.algorithm.linkedList;

import java.util.*;

/**
 * 重排链表
 */
public class ReorderList {

    /**
     * 数组模拟
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();

        ListNode cur = head;

        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }


        List<ListNode> reversedList = new ArrayList<>();

        for (int i = 0; i < list.size() / 2; i++) {
            reversedList.add(list.get(i));
            reversedList.add(list.get(list.size() - i - 1));
        }

        if (list.size() % 2 != 0) {
            reversedList.add(list.get(list.size() / 2));
        }

        cur = head;

        for (int i = 1; i < reversedList.size(); i++) {
            cur.next = reversedList.get(i);
            cur = cur.next;
        }
        cur.next = null;
    }


    /**
     * 双向链表
     */
    public void reorderList1(ListNode head) {
        Deque<ListNode> queue = new ArrayDeque<>();

        ListNode cur = head;
        while (cur != null) {
            queue.addLast(cur);
            cur = cur.next;
        }

        cur = head;

        while (!queue.isEmpty()) {
            cur.next = queue.pollFirst();
            cur = cur.next;
            if (!queue.isEmpty()) {
                cur.next = queue.pollLast();
                cur = cur.next;
            }
        }
    }
}

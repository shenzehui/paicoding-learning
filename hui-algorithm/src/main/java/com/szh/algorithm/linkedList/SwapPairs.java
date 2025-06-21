package com.szh.algorithm.linkedList;

/**
 * 两两交换链表中的节点
 */
public class SwapPairs {

    public ListNode swapPairs(ListNode head) {
        ListNode dumyHead = new ListNode();
        dumyHead.next = head;
        ListNode temp = dumyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode cur = temp.next;
            ListNode cur2 = temp.next.next.next;

            temp.next = temp.next.next;
            cur.next.next = cur;
            cur.next = cur2;
            temp = temp.next.next;
        }
        return dumyHead.next;
    }

}

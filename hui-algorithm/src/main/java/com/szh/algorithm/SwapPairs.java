package com.szh.algorithm;

public class SwapPairs {

    class ListNode {

        int val;

        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    int size;

    ListNode head;

    public void MyLinkedList() {
        this.size = 0;
        head = new ListNode(0);
    }

    /**
     * 两两交换链表中的节点
     */
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

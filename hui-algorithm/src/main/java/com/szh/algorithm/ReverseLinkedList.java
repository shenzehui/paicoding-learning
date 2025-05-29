package com.szh.algorithm;

public class ReverseLinkedList {

    public static class ListNode {

        int val;

        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 双指针法
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 递归法
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        return reverse(cur, pre);
    }

    public ListNode reverse(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        ListNode temp = cur.next;
        cur.next = pre;
        return reverse(temp, cur);
    }
}

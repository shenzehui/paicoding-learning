package com.szh.algorithm;

public class RemoveNthFromEnd {


    class ListNode {

        int val;

        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 删除链表的倒数第N个节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dumyHead = new ListNode();
        dumyHead.next = head;
        ListNode temp = dumyHead;

        // 快慢指针
        ListNode slow = temp;
        ListNode fast = temp;

        // 快指针先移动n+步
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dumyHead.next;

    }

}

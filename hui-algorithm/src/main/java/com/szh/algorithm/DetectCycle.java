package com.szh.algorithm;

public class DetectCycle {

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
     * 环形链表
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode dumyHead = new ListNode();
        dumyHead.next = head;

        ListNode temp = dumyHead;

        ListNode slow = dumyHead;
        ListNode fast = dumyHead;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                // 相遇了，表示有环
                ListNode index = fast;
                while (temp != index) {
                    temp = temp.next;
                    index = index.next;
                }

                return temp;
            }
        }
        return null;
    }
}

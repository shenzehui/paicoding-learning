package com.szh.algorithm.linkedList;

/**
 * 环形链表 II
 */
public class DetectCycle {

    /**
     * 快慢指针
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

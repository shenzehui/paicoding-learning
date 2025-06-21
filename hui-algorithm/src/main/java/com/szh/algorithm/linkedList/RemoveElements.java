package com.szh.algorithm.linkedList;

/**
 * 删除链表中等于给定值 val 的所有节点。
 */
public class RemoveElements {

    /**
     * 删除链表中等于给定值 val 的所有节点。
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        // 删除头结点
        while (head != null && head.val == val) {
            head = head.next;
        }

        // 临时节点，用于遍历
        ListNode cur = head;
        // 删除中间节点
        while (cur != null && cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;

    }

    /**
     * 虚拟节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements1(ListNode head, int val) {
        ListNode node = new ListNode();
        node.next = head;
        ListNode cur = node;

        while (cur != null && cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }

        }
        return node.next;

    }

}

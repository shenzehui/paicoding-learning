package com.szh.algorithm.linkedList;

/**
 * 160. 相交链表
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int countA = 0;
        int countB = 0;

        ListNode curA = headA;
        ListNode curB = headB;

        while (headA != null) {
            countA++;
            headA = headA.next;
        }

        while (headB != null) {
            countB++;
            headB = headB.next;
        }

        if (countA > countB) {
            int count = countA - countB;
            while (count-- > 0 && curA != null) {
                curA = curA.next;
            }
        } else {
            int count = countB - countA;
            while (count-- > 0 && curB != null) {
                curB = curB.next;
            }
        }

        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }

        return curA;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != curB) {
            if (curA == null) {
                curA = headB;
            } else {
                curA = curA.next;
            }

            if (curB == null) {
                curB = headA;
            } else {
                curB = curB.next;
            }
        }
        return curA;
    }
}

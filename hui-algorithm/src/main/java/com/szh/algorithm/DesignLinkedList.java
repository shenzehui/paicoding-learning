package com.szh.algorithm;

public class DesignLinkedList {

    class ListNode {
        int val;
        ListNode next;

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

    public int get(int index) {
        // 非法，返回-1
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = head;
        // 多遍历一遍，存在虚拟节点
        for (int i = 0; i < index + 1; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head.next;
        head.next = newNode;
        size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        // 找到尾节点
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
        size++;
    }

    public void addAtIndex(int index, int val) {
        // 非法，返回
        if (index < 0 || index > size) {
            return;
        }
        ListNode newNode = new ListNode(val);
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        // 非法，返回
        if (index < 0 || index >= size) {
            return;
        }
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        size--;
    }

}

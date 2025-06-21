package com.szh.algorithm.linkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 *
 * @author szh
 **/
public class PalindromeLinkedList {

    /**
     * 将链表转成数组，再判断数组是否是回文数组
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }


}

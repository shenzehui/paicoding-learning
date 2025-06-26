package com.szh.algorithm.arrays;

/**
 * 移除元素
 *
 * @author szh
 */
public class RemoveElement {

    /**
     * 暴搜
     */
    public int removeElement(int[] nums, int val) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == val) {
                for (int j = i; j < size - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                i--;   // 覆盖元素后，索引需要减一
                size--;  //  数组长度减一
            }
        }
        return size;
    }

    /**
     * 双指针法（快慢指针）
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement1(int[] nums, int val) {
        int fast = 0;  // 快指针，指向数据中每个元素下标
        int slow = 0;  // 慢指针，指向重新赋值数据元素下标（也就是数组长度）
        for (int i = 0; i < nums.length; i++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 4, 5, 6, 7, 5, 9, 10};
        RemoveElement removeElement = new RemoveElement();
        int i = removeElement.removeElement1(nums, 5);
        System.out.println(i);
    }
}

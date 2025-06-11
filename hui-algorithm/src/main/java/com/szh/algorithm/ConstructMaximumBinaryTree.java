package com.szh.algorithm;

import java.util.Arrays;

public class ConstructMaximumBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode() {
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 最大二叉树
     *
     * @param nums
     * @return
     */
    public TreeNode construct(int[] nums) {
        // 叶子节点，直接返回
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }

        int index = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        if (index > 0) {
            root.left = construct(Arrays.copyOfRange(nums, 0, index));
        }
        if (index < nums.length - 1) {
            root.right = construct(Arrays.copyOfRange(nums, index + 1, nums.length));
        }
        return root;
    }

}

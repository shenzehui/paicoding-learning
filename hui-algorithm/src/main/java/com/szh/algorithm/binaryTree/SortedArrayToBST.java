package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

/**
 * 108. 将有序数组转换为二叉搜索树
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return traversal(nums, 0, nums.length - 1);
    }

    public TreeNode traversal(int[] nums, int left, int right) {
        // left == right 说明是叶子节点
        if (left > right) {
            return null;
        }
        int middle = (left + right) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = traversal(nums, left, middle - 1);
        root.right = traversal(nums, middle + 1, right);

        return root;
    }

}

package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

/**
 * 98. 验证二叉搜索树
 */
public class IsValidBST {

    long maxValue = Long.MIN_VALUE;

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 左
        boolean left = isValidBST(root.left);
        // 中
        if (root.val > maxValue) {
            maxValue = root.val;
        } else {
            return false;
        }
        // 右
        boolean right = isValidBST(root.right);

        return left && right;
    }


    TreeNode pre = null;

    /**
     * 递归（双指针改进）
     *
     * @param root
     * @return
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 左
        boolean left = isValidBST1(root.left);
        // 中
        if (pre == null || root.val > pre.val) {  // 前一个节点的值小于当前节点的值
            pre = root;
        } else {
            return false;
        }
        // 右
        boolean right = isValidBST1(root.right);

        return left && right;
    }

    public static void main(String[] args) {
        IsValidBST isValidBST = new IsValidBST();
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(3);
        treeNode.right.right = new TreeNode(6);
        System.out.println(isValidBST.isValidBST1(treeNode));
    }
}

package com.szh.algorithm;

public class CountNodes {

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
     * 完全二叉树的节点的数量
     * 递归
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftNum = countNodes(root.left);  // 左
        int rightNum = countNodes(root.right); // 右

        return leftNum + rightNum + 1; // 中
    }

    /**
     * 完全二叉树的节点的数量
     * 递归
     *
     * @param root
     * @return
     */
    public int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        int leftHeight = 0, rightHeight = 0;
        while (leftNode != null) {
            leftNode = leftNode.left;
            leftHeight++;
        }
        while (rightNode != null) {
            rightNode = rightNode.right;
            rightHeight++;
        }
        // 中
        if (leftHeight == rightHeight) {
            return (2 << leftHeight) - 1;
        }
        // 左
        int left = countNodes1(root.left); // 左
        // 右
        int right = countNodes1(root.right); // 右
        // 中
        return left + right + 1;  // 中

    }


}

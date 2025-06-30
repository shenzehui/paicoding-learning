package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

/**
 * 110. 平衡二叉树
 */
public class BalancedBinaryTree {

    /**
     * 是否为平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    /**
     * 递归
     * <p>
     * -1 表示已经不是平衡二叉树了，否则返回值是以该节点为根节点树的高度
     *
     * @param root
     * @return
     */
    public int getHeight(TreeNode root) {
        // 终止条件
        if (root == null) {
            return 0;
        }
        // 左
        int leftHeight = getHeight(root.left);
        // 终止条件，提前终止
        if (leftHeight == -1) {
            return -1;
        }
        // 右
        int rightHeight = getHeight(root.right);
        // 终止条件，提前终止
        if (rightHeight == -1) {
            return -1;
        }
        // 中
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}

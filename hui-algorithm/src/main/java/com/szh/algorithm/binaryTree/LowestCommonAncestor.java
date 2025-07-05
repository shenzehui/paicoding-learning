package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

/**
 * 二叉树的最近公共祖先
 */
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 终止条件
        if (root == null) {
            return null;
        }
        // 遍历到的节点为 p 和 q ，提前返回当前节点
        if (root == p || root == q) {
            return root;
        }

        // 左
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // 右
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 中
        if (left != null && right != null) {
            return root;
        }

        if (left == null && right != null) {
            return right;
        } else if (left != null && right == null) {
            return left;
        } else {
            return null;
        }

    }


}

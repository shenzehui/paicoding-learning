package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

/**
 * 450. 删除二叉搜索树中的节点
 **/
public class DeleteNodeIntoBST {

    // 递归逻辑
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                // 叶子节点
                return null;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else {
                TreeNode cur = root.right;
                while (cur.left != null) {
                    // 向左遍历
                    cur = cur.left;
                }
                cur.left = root.left;
                return root.right;
            }
        }

        // 递归逻辑
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }
}

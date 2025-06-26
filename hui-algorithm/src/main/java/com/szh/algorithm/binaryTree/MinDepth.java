package com.szh.algorithm.binaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 111. 二叉树的最小深度
 */
public class MinDepth {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 层序遍历，效率太低了
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {

        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) {
            return 0;
        }
        queue.addLast(root);
        int minHeight = Integer.MAX_VALUE;
        int height = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;
            while (size-- > 0) {
                TreeNode node = queue.pollFirst();
                if (node.left == null && node.right == null) {
                    minHeight = Math.min(minHeight, height);
                }

                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return minHeight;
    }


    /**
     * 递归（后续遍历）
     *
     * @param root
     * @return
     */
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = minDepth1(root.left); // 左
        int rightHeight = minDepth1(root.right); // 右
        if (root.left == null && root.right != null) {
            return rightHeight + 1;
        } else if (root.left != null && root.right == null) {
            return leftHeight + 1;
        } else {
            return Math.min(leftHeight, rightHeight) + 1; // 中
        }
    }
}

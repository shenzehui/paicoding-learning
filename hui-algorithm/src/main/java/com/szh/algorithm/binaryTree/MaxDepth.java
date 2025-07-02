package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 104. 二叉树的最大深度
 */
public class MaxDepth {

    /**
     * 二叉树的最大深度（左右中的遍历顺序）
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);  // 左
        int right = maxDepth(root.right); // 右
        return Math.max(left, right) + 1; // 中
    }

    /**
     * 前序遍历方式
     */
    int result = 0;

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getDepth(root, 1);
        return result;
    }

    public void getDepth(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            return;
        }
        result = Math.max(result, depth);
        if (root.left != null) {
            depth++;
            getDepth(root.left, depth);
            depth--;  // 回溯
        }

        if (root.right != null) {
            depth++;
            getDepth(root.right, depth);
            depth--; // 回溯
        }
    }


    /**
     * 层序遍历
     */
    public int maxDepth1(TreeNode root) {
        int maxDepth = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.addFirst(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.pollFirst();
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            maxDepth++;
        }

        return maxDepth;
    }

}

package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 513. 找树左下角的值
 */
public class FindBottomLeftValue {

    /**
     * 513. 找树左下角的值  迭代法实现
     * <p>
     * 给定一个二叉树，在树的最后一行找到最左边的值。
     * <p>
     * 示例 1:
     * <p>
     * <p>
     * 输入:
     * <p>
     * 2
     * / \
     * 1   3
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        int res = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                if (i == 0) {
                    res = node.val;
                }
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                }
            }
        }
        return res;
    }

    /**
     * 513. 找树左下角的值  递归法实现
     * <p>
     * 给定一个二叉树，在树的最后一行找到最左边的值。
     * <p>
     * 示例 1:
     * <p>
     * <p>
     * 输入:
     * <p>
     * 2
     * / \
     * 1   3
     */
    int res = 0;
    int maxDepth = Integer.MIN_VALUE;

    public int findBottomLeftValue1(TreeNode root) {
        traversal(root, 0);
        return res;
    }


    // 这题前序、中序、后续都可以
    void traversal(TreeNode root, int depth) {
        // 终止条件
        if (root.left == null && root.right == null) {
            if (depth > maxDepth) {
                maxDepth = depth;
                res = root.val;
            }
        }

        // 左
        if (root.left != null) {
            depth++;
            traversal(root.left, depth);
            depth--; // 回溯
        }
        // 右
        if (root.right != null) {
            // 精减版
            traversal(root.right, depth + 1);
//            depth++;
//            traversal(root.right, depth);
//            depth--; // 回溯
        }

    }

    public static void main(String[] args) {
        FindBottomLeftValue findBottomLeftValue = new FindBottomLeftValue();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(findBottomLeftValue.findBottomLeftValue(root));

    }

}

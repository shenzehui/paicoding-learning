package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 222.完全二叉树的节点个数
 */
public class CountNodes {

    /**
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
     * 递归（优化）
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
            // 使用左移运算符 << 来实现2的n次方的计算 2 >> 0 -1  = 1
            return (2 << leftHeight) - 1;
        }
        // 左
        int left = countNodes1(root.left); // 左
        // 右
        int right = countNodes1(root.right); // 右
        // 中
        return left + right + 1;  // 中

    }

    /**
     * 迭代
     */
    public int countNodes2(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.addFirst(root);
        }
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.pollFirst();
                count++; // 收集结果
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        int i = new CountNodes().countNodes1(root);
        System.out.println(i);

//        System.out.println((2 << 0) - 1);
    }


}

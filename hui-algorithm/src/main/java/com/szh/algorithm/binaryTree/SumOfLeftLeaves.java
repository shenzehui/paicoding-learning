package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 404. 左叶子之和
 */
public class SumOfLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 可以不写，写了就是少一层递归
        if (root.left == null && root.right == null) {
            return 0;
        }

        int leftNum = sumOfLeftLeaves(root.left);
        int rightNum = sumOfLeftLeaves(root.right);

        if (root.left != null && root.left.left == null && root.left.right == null) {
//            leftNum = root.left.val;
            return root.left.val + rightNum;
        }

        return leftNum + rightNum;
    }

    /**
     * 迭代
     */
    public int sumOfLeftLeaves1(TreeNode root) {
        int res = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.addLast(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.pollFirst();
                if (node.left != null && node.left.left == null && node.left.right == null) {
                    res += node.left.val;
                }
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        SumOfLeftLeaves sumOfLeftLeaves = new SumOfLeftLeaves();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(sumOfLeftLeaves.sumOfLeftLeaves(root));

    }

}

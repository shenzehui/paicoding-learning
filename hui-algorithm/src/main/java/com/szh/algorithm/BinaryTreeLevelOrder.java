package com.szh.algorithm;

import java.util.*;

public class BinaryTreeLevelOrder {

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
     * 102. 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 定义一个队列
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode treeNode = queue.pollFirst();
                list.add(treeNode.val);

                if (treeNode.left != null) {
                    queue.addLast(treeNode.left);
                }

                if (treeNode.right != null) {
                    queue.addLast(treeNode.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}

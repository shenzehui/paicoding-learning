package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 226.翻转二叉树
 */
public class InvertTree {


    /**
     * 前序遍历
     * <p>
     * 前序和后序都可以，中序不行，遍历到中节点交换后，右孩子节点以及变成左节点了，
     * 再右孩子交换孩子（此时其实是对原来的左孩子做交换），相当于没有交换。
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        // 终止条件
        if (root == null) {
            return null;
        }
        swap(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public void swap(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }


    /**
     * BFS 层序遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.addFirst(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.pollFirst();
                swap(node);

                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return root;
    }
}

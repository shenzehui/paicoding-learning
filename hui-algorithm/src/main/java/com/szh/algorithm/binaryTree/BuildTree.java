package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

import java.util.Arrays;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 */
public class BuildTree {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 后续数组为空，返回空节点
        if (postorder.length == 0) {
            return null;
        }
        // 后续数组中最后一个节点
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        if (postorder.length == 1) {
            return root;
        }
        int index = 0;  // 中序数组中根节点的索引
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                index = i;
                break;
            }
        }
        root.left = buildTree(
                // 左子树中序数组
                Arrays.copyOfRange(inorder, 0, index),
                // 左子树后序数组
                Arrays.copyOfRange(postorder, 0, index)
        );

        root.right = buildTree(
                // 右子树中序数组
                Arrays.copyOfRange(inorder, index + 1, inorder.length),
                // 右子树后序数组
                Arrays.copyOfRange(postorder, index, postorder.length - 1)
        );
        return root;
    }

    public static void main(String[] args) {
        BuildTree buildTree = new BuildTree();
        TreeNode treeNode = buildTree.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        System.out.println(treeNode);
    }
}

package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

/**
 * 669. 修剪二叉搜索树
 */
public class TrimBST {


    /**
     * 递归
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 当前节点的左子树全部剪枝，右子树递归再遍历
//        TreeNode root1 = new TreeNode(root.val);
        if (root.val < low) {
            TreeNode node = trimBST(root.right, low, high);
            return node;
        }
        if (root.val > high) {
            TreeNode node = trimBST(root.left, low, high);
            return node;
        }

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }

    public static void main(String[] args) {
        TrimBST trimBST = new TrimBST();
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.right = new TreeNode(3);
        treeNode.right.right.right = new TreeNode(4);
        System.out.println(trimBST.trimBST(treeNode, 1, 6));
    }
}

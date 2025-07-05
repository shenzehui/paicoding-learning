package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

/**
 * 530. 二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 * 提示：
 * 树中节点的数目范围是 [2, 104]
 */
public class GetMinimumDifference {

    TreeNode pre = null;

    Integer minValue = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左
        getMinimumDifference(root.left);

        // 中
        if (pre != null && (root.val - pre.val) < minValue) {
            minValue = root.val - pre.val;
        }
        pre = root;

        // 右
        getMinimumDifference(root.right);

        return minValue;

    }

    public static void main(String[] args) {
        GetMinimumDifference getMinimumDifference = new GetMinimumDifference();
//        TreeNode treeNode = new TreeNode(1);
//        treeNode.left = new TreeNode(0);
//        treeNode.right = new TreeNode(48);
//        treeNode.right.left = new TreeNode(12);
//        treeNode.right.right = new TreeNode(49);
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(3);
        treeNode.right = new TreeNode(6);
        System.out.println(getMinimumDifference.getMinimumDifference(treeNode));

    }
}

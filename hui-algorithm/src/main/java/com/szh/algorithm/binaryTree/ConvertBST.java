package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

/**
 * 538. 把二叉搜索树转换为累加树
 */
public class ConvertBST {

    TreeNode pre = null;

    /**
     * 递归
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 右
        convertBST(root.right);

        // 中
        if (pre != null) {
            root.val += pre.val;
        }
        pre = root;

        // 左
        convertBST(root.left);

        return root;
    }
}

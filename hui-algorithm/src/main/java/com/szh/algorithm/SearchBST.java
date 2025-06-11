package com.szh.algorithm;

public class SearchBST {

    /**
     * 递归
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }


    /**
     * 迭代
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBS1(TreeNode root, int val) {
        while (root != null){
            if (root.val == val) {
                return root;
            } else if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }
}

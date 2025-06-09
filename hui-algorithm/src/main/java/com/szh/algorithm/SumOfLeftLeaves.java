package com.szh.algorithm;

public class SumOfLeftLeaves {

    public static class TreeNode {
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
            leftNum = root.left.val;
        }

        return leftNum + rightNum;
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

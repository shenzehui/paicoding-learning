package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

/**
 * 112. 路径总和
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && targetSum == root.val) {
            return true;
        }
        return traversal(root, targetSum - root.val);
    }

    public boolean traversal(TreeNode root, int targetSum) {
        // 终止条件
        if (root.left == null && root.right == null) {
            if (targetSum == 0) {
                return true;
            }
            return false;
        }
        if (root.left != null) {
            targetSum -= root.left.val;
            if (traversal(root.left, targetSum)) {   // 返回给根节点是否有路径
                return true;
            }
            targetSum += root.left.val; // 回溯
        }
        if (root.right != null) {
            targetSum -= root.right.val;
            if (traversal(root.right, targetSum)) {   // 返回给根节点是否有路径
                return true;
            }
            // 其实这一行代码没什么用了，不需要再回溯，因为拿到了叶子节点后，要么是返回true，要么就是返回false，回溯没意思了
            targetSum += root.right.val; // 回溯
        }
        return false;
    }

    public static void main(String[] args) {
        PathSum pathSum = new PathSum();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(10);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(1);
        System.out.println(pathSum.hasPathSum(root, 22));

    }
}

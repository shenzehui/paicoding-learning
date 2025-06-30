package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

/**
 * 101. 对称二叉树
 */
public class SymmetricTree {

    // 后续遍历
    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }


    /**
     * 递归实现   后续遍历，只有把左右孩子比较完了，才能去判断中节点是否堆成
     * <p>
     * <p>
     * 左子树遍历顺序是 左右中  右子树遍历顺序是 右左中
     *
     * @param leftNode
     * @param rightNode
     * @return
     */
    public boolean compare(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode != null) {
            return false;
        } else if (leftNode != null && rightNode == null) {
            return false;
        } else if (leftNode == null && rightNode == null) {  // 终止条件
            return true;
        } else if (leftNode.val != rightNode.val) {
            return false;
        }
        // 这里不要去判断 leftNode.val == rightNode.val
        boolean outSide = compare(leftNode.left, rightNode.right);  // 左
        boolean inSide = compare(leftNode.right, rightNode.left);  // 右
        return outSide && inSide;  // 中

    }

}

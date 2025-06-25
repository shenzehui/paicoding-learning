package com.szh.algorithm.dp;

/**
 * 337. 打家劫舍 III
 */
public class HouseRobberIII {

    class TreeNode {

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

    public int rob(TreeNode root) {
        int[] res = robbTree(root);
        return Math.max(res[0], res[1]);
    }


    /**
     * 后续递归遍历
     *
     * @param cur 树节点
     * @return dp数组
     */
    public int[] robbTree(TreeNode cur) {
        if (cur == null) {
            return new int[]{0, 0};
        }

        // 左
        int[] left = robbTree(cur.left);
        // 右
        int[] right = robbTree(cur.right);
        // 中

        // 偷
        int fetch = left[0] + right[0] + cur.val;
        // 不偷当前节点，左右孩子不一定是一定要偷的!!!，要取偷和不偷的最大值。
        int unFetch = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{unFetch, fetch};
    }

}

package com.szh.algorithm.dp;

import java.util.HashMap;
import java.util.Map;

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
//        return Math.max(res[0], res[1]);
        return robAction(root, new HashMap<>());
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

    /**
     * 记忆化递推
     *
     * @param root 树根节点
     * @return 偷的最大值
     */
    public int robAction(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        // 偷的情况
        int money = root.val;
        // 左
        if (root.left != null) {
            money += robAction(root.left.left, memo) + robAction(root.left.right, memo);
        }
        // 右
        if (root.right != null) {
            money += robAction(root.right.left, memo) + robAction(root.right.right, memo);
        }

        // 不偷当前节点情况
        int unFetch = robAction(root.right, memo) + robAction(root.left, memo);
        // 当前节点取最大值
        int res = Math.max(money, unFetch);
        memo.put(root, res);
        return res;

    }

}

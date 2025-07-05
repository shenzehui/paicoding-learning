package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 501. 二叉搜索树中的众数
 */
public class FindMode {

    int count = 1;
    int maxCount = 0;
    TreeNode pre = null;

    List<Integer> res = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        traversal(root);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public void traversal(TreeNode root) {
        if (root == null) {
            return;
        }
        traversal(root.left);
        if (pre != null && pre.val == root.val) {
            count++;
        } else {
            count = 1;
        }

        if (count == maxCount) {
            res.add(root.val);
        }

        if (count > maxCount) {
            maxCount = count;
            res.clear();
            res.add(root.val);
        }
        pre = root;
        traversal(root.right);
    }
}

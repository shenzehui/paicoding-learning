package com.szh.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreorderTraversal {

    // 树结构其实就是链表
    public class TreeNode {
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

    /**
     * 前序遍历  中左右
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traversal(root, res);
        return res;
    }


    /**
     * 前序遍历
     * 1. 确定递归函数的参数和返回值：确定哪些参数是递归的过程中需要处理的，那么就在递归函数里加上这个参数，并且还要明确每次递归的返回值是什么进而确定递归函数的返回类型。
     * <p>
     * 2. 确定终止条件：写完了递归算法，运行的时候，经常会遇到栈溢出的错误，就是没写终止条件或者终止条件写的不对，操作系统也是用一个栈的结构来保存每一层递归的信息，如果递归没有终止，操作系统的内存栈必然就会溢出。
     * <p>
     * 3. 确定单层递归的逻辑： 确定每一层递归需要处理的信息。在这里也就会重复调用自己来实现递归的过程。
     *
     * @param root
     * @param res
     */
    public void traversal(TreeNode root, List<Integer> res) {
        // 终止条件
        if (root == null) {
            return;
        }
        res.add(root.val); // 中
        traversal(root.left, res); // 左
        traversal(root.right, res); // 右
    }


    /**
     * 后序遍历
     *
     * @param root
     * @param res
     */
    public void traversal1(TreeNode root, List<Integer> res) {
        // 终止条件
        if (root == null) {
            return;
        }
        traversal(root.left, res); // 左
        traversal(root.right, res); // 右
        res.add(root.val); // 中
    }


    /**
     * 前序遍历（迭代法）
     * 中左右
     *
     * @param root
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        // 定义一个栈
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
//        res.add(root.val);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 可以再pop后判断null，也可以在push的时候判断null。
            if (node != null) {
                res.add(node.val);
            } else {
                continue;
            }
            // 先放右节点
            stack.push(node.right);
            // 再放左节点
            stack.push(node.left);
        }
        return res;
    }

    /**
     * 后续遍历（迭代法）
     * 左右中
     * <p>
     * 中左右-> 中右左-> 左右中
     *
     * @param root
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        // 定义一个栈
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
//        res.add(root.val);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 可以再pop后判断null，也可以在push的时候判断null。
            if (node != null) {
                res.add(node.val);
            } else {
                continue;
            }
            // 先放左节点
            stack.push(node.left);
            // 再放右节点
            stack.push(node.right);

        }
        return reverse(res);
    }


    /**
     * 翻转数组
     *
     * @param res
     * @return
     */
    public List<Integer> reverse(List<Integer> res) {
        int left = 0;
        int right = res.size() - 1;
        while (left < right) {
            int temp = res.get(left);
            res.set(left, res.get(right));
            res.set(right, temp);
            left++;
            right--;
        }
        return res;
    }


    /**
     * 中序遍历（迭代法）
     * 左中右
     *
     * @param root
     */
    public List<Integer> preorderTraversal4(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 定义一个栈
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        TreeNode cur = root.left;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }
}

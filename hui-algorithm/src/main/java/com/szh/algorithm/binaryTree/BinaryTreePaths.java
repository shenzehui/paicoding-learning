package com.szh.algorithm.binaryTree;

import com.szh.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 257. 二叉树的所有路径
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        List<String> res = new ArrayList<>();
        recursion(root, path, res);
        return res;
    }


    /**
     * 递归，针对每一次递归，进行一次回溯
     *
     * @param root
     * @param path
     * @param res
     */
    public void recursion(TreeNode root, List<Integer> path, List<String> res) {
        // 中
        path.add(root.val);
        // 终止条件
        if (root.left == null && root.right == null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                stringBuilder.append(path.get(i)).append("->");
            }
            stringBuilder.append(path.get(path.size() - 1)); // 最后一个
            res.add(stringBuilder.toString());
            return;
        }

        // 左
        if (root.left != null) {
            recursion(root.left, path, res);
            // 回溯
            path.remove(path.size() - 1);

        }
        // 右
        if (root.right != null) {
            recursion(root.right, path, res);
            // 回溯
            path.remove(path.size() - 1);
        }
    }


    List<String> result = new ArrayList<>();

    public List<String> binaryTreePaths1(TreeNode root) {
        deal(root, "");
        return result;
    }

    /**
     * 递归 精简写法，难理解
     *
     * @param node
     * @param s
     */
    public void deal(TreeNode node, String s) {
        if (node == null)
            return;
        if (node.left == null && node.right == null) {
            result.add(new StringBuilder(s).append(node.val).toString());
            return;
        }
        String tmp = new StringBuilder(s).append(node.val).append("->").toString();  // 这里隐藏了回溯，没有对字符串S进行修改
        deal(node.left, tmp);
        deal(node.right, tmp);
    }

    /**
     * 迭代，利用栈模拟
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        Stack<Object> stack = new Stack<>();
        if (root == null) {
            return res;
        }
        // 节点和路径同时入栈   节点用于判断是否是叶子节点，路径是为了收集结果
        stack.push(root);
        stack.push(root.val + "");

        while (!stack.isEmpty()) {
            String path = (String) stack.pop();
            TreeNode node = (com.szh.algorithm.TreeNode) stack.pop();

            // 判断是否是叶子节点
            if (node.left == null && node.right == null) {
                // 收集结果
                res.add(path);
            }

            // 这样的话可以先收集左子树，再收集右子树，因为右子树先入栈。
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }

            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }

        }
        return res;
    }

    public static void main(String[] args) {
        BinaryTreePaths binaryTreePaths = new BinaryTreePaths();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        System.out.println(binaryTreePaths.binaryTreePaths1(root));
    }
}

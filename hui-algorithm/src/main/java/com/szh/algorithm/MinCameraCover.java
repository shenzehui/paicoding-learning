package com.szh.algorithm;

/**
 * Title: 监控二叉树
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class MinCameraCover {
    public int minCameraCover(TreeNode root) {
        int state = travesal(root);
        if (state == 0) {
            result++;
        }
        return result;

    }

    int result = 0;

    //定义三种状态：0-无覆盖 1-有摄像头 2-有覆盖
    public int travesal(TreeNode cur) {
        if (cur == null) {
            return 2;
        }
        // 左
        int left = travesal(cur.left);
        // 右
        int right = travesal(cur.right);
        // 中

        // 情况1；左右孩子都右覆盖，根节点为无覆盖
        if (left == 2 && right == 2) {
            return 0;
        }
        // 情况2：左右孩子只少有一个为无覆盖，根节点为摄像头
        if (left == 0 || right == 0) {
            result++;
            return 1;
        }
        // 情况3：左右孩子只少有一个摄像头，根节点为有覆盖
        if (left == 1 || right == 1) {
            return 2;
        }

        return -1;

    }

}

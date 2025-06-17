package com.szh.algorithm;

/**
 * Title: 跳跃游戏II
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class JumpGameII {

    int cur = 0;
    int cover = 0;
    int result = 0;

    public int jump(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            cover = Math.max(cover, nums[i] + i);
            if (i == cur) {
                if (cur != nums.length - 1) {
                    result++;
                    cur = cover;
                    if (cur >= nums.length - 1) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return result;
    }
}

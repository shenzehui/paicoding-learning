package com.szh.algorithm;

/**
 * Title: 跳跃游戏
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class JumpGame {

    int cover = 0;

    public boolean canJump(int[] nums) {
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, nums[i] + i);
            if (cover >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }


}

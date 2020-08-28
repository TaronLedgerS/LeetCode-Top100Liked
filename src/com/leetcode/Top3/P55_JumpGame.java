package com.leetcode.Top3;

public class P55_JumpGame {
    public boolean canJump(int[] nums) {
        int maxEnd = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i>maxEnd) return false;
            maxEnd = Math.max(maxEnd, i + nums[i]);
            if (maxEnd>=nums.length-1) break;
        }
        return true;
    }
}

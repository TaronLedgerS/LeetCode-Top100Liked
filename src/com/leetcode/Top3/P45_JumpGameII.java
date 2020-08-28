package com.leetcode.Top3;

public class P45_JumpGameII {
    public int jump(int[] nums) {
        int step = 0, nextRangeEnd = 0, maxEnd = 0;
        for (int i = 0; i < nums.length-1; i++) {
            nextRangeEnd = Math.max(nextRangeEnd, nums[i] + i);
            if (i == maxEnd) {
                step++;
                maxEnd = nextRangeEnd;
                if (maxEnd >= nums.length - 1) {
                    break;
                }
            }
        }
        return step;
    }
}

package com.leetcode.Top2;

public class P42_TrappingRainWater {
    public int trap(int[] height) {
        int n = height.length;
        if (n==0) return 0;
        int[] maxl = new int[n];
        int[] maxr = new int[n];
        int sum = 0;
        for (int i = 0;i<n;i++)
            maxl[i] = i==0?height[i]:Math.max(maxl[i-1],height[i]);
        for (int i = n-1;i>=0;i--)
            maxr[i] = i==n-1?height[i]:Math.max(maxr[i+1],height[i]);
        for (int i = 0; i < n; i++) {
            sum += Math.min(maxl[i], maxr[i]) - height[i];
        }
        return sum;
    }
}

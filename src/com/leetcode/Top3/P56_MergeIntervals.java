package com.leetcode.Top3;

import java.util.ArrayList;
import java.util.Arrays;

public class P56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length<=1) return intervals;
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        ArrayList<int[]> result = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];
        for (int i = 1; i < intervals.length ; i++) {
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                result.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        result.add(new int[]{start, end});
        System.out.println(result.size());
        int[][] res = new int[result.size()][2];
        int i = 0;
        for (int[] a : result) {
            res[i++] = a;
        }
        return res;
    }
}

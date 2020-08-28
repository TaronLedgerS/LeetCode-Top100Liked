package com.leetcode.Top3;

public class P48_RotateImage {
    public void rotate(int[][] matrix) {
        int start = 0;
        int gap = matrix.length - 1;
        while (gap > 0) {
            for (int i = 0; i < gap; i++) {
                int tmp = matrix[start][start + i];
                matrix[start][start + i] = matrix[start + gap - i][start];
                matrix[start + gap - i][start] = matrix[start + gap ][start + gap - i];
                matrix[start + gap][start + gap - i] = matrix[start+i][start + gap];
                matrix[start+i][start + gap] = tmp;
            }
            gap -= 2;
            start += 1;
        }
    }
}

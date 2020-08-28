package com.leetcode.Top3;

public class P64_MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = grid[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j==0 ) continue;
                if (i ==0)
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + grid[i][j]);
                if (j == 0)
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + grid[i][j]);
                if (i!=0&&j!=0)
                    dp[i][j] = Math.min(dp[i][j - 1] + grid[i][j], dp[i-1][j] + grid[i][j]);
            }
        }
        return dp[n - 1][m - 1];
    }
}

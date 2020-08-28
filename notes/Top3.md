[TOC]

# LeetCode-Top21_30

-   Problems-ID: 45、46、48、49、 **53** 、55、56、62、64、 **70**

## [EASY DONE]53. Maximum Subarray

## [EASY DONE]70. Climbing Stairs

## [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/)

### 题解（bfs、贪心）-2020年9月3日

```java
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
```

## [46. Permutations](https://leetcode.com/problems/permutations/)

### 题解（dfs）-2020年9月4日

```java
public class P46_Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, nums,new ArrayList<>(),new boolean[nums.length],0);
        return result;
    }

    private void dfs(List<List<Integer>> result, int[] nums,
                     ArrayList<Integer> cur,boolean[] visited, int i) {
        if (i == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if (!visited[j]) {
                visited[j] = true;
                cur.add(nums[j]);
                dfs(result, nums, cur, visited, i + 1);
                cur.remove(cur.size() - 1);
                visited[j] = false;
            }
        }
    }
}
```

## [48. Rotate Image](https://leetcode.com/problems/rotate-image/)

### 题解（数组下标）-2020年9月5日

[五分钟力扣 Leetcode 第48题 旋转图像 清晰易懂 例子阐述 时间67%](https://www.bilibili.com/video/BV1Ct4y1C7zf?from=search&seid=12446541316427512348)

```java
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
```

## [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)

### 题解（字符串、哈希key）-2020年9月6日

```java
public class P49_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List> ans = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(strs[i]);
        }
        return new ArrayList(ans.values());
    }
}
```

## [55. Jump Game](https://leetcode.com/problems/jump-game/)

### 题解（贪心）-2020年9月7日

```java
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
```

## [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)

### 题解（排序）-2020年9月8日

```java
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
```

## [62. Unique Paths](https://leetcode.com/problems/unique-paths/)

### 题解（简单DP）-2020年9月9日

```java
public class P62_UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j-1];
            }
        }
        return dp[m][n];
    }
}
```

## [64. Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/)

### 题解（简单DP）-2020年9月10日

```java
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
```

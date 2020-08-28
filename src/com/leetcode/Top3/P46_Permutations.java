package com.leetcode.Top3;

import java.util.ArrayList;
import java.util.List;

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

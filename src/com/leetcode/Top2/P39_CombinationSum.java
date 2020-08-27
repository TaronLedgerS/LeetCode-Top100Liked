package com.leetcode.Top2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class P39_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(result, cur, candidates, target,0);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> cur, int[] candidates, int target,int start) {
        if (target == 0) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) {
                cur.add(candidates[i]);
                dfs(result, cur, candidates, target - candidates[i],i);
                cur.remove(cur.size() - 1);
            }
        }
    }
}

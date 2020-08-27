package com.leetcode.Top2;

import java.util.ArrayList;
import java.util.List;

public class P22_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(n, result, new StringBuilder(), 0, 0, 0);
        return result;
    }

    private void dfs(int n, List<String> result, StringBuilder cur, int start, int left, int right) {
        if (start == 2 * n) {
            result.add(cur.toString());
            return;
        }
        if (left < n) {
            cur.append("(");
            dfs(n, result, cur, start + 1, left + 1, right);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (right < left) {
            cur.append(")");
            dfs(n, result, cur, start + 1, left , right+1);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}

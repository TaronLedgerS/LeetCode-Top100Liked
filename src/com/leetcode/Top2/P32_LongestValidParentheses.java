package com.leetcode.Top2;

import java.util.Stack;

public class P32_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()){
                    stack.push(i);
                }else{
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }

}

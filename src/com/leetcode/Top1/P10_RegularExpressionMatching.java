package com.leetcode.Top1;

public class P10_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        // dp[i][j] s长度为i的子串 与 p长度为j的子串的匹配情况
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        // 默认匹配 如s:"" p:""
        dp[0][0] = true;
        // j==0的情况，dp[i][0] == false 如s:"abc" p:""
        // i==0的情况，如s:"" p:".*" 、"a*a*"
        for (int j = 1; j <= p.length(); j++) {
            // 先找*，再找到*前面的字符一起消掉不看 --- 选择匹配0次，因为i==0
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        // 从i>1、j>1开始遍历
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                // 情况一：两个字母或者一字母一'.'的情况
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 情况二：p串出现*
                if (p.charAt(j - 1) == '*') {
                    // 情况2.1：*前面一对字母不匹配、或者不是'.'，只能选择匹配0次即消除掉a*.*这些
                    if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j-2];
                    }else{//情况2.2:匹配上了，可以来自于前一对字母匹配0、1、多个
                        dp[i][j] = (dp[i][j-2] || dp[i][j-1] || dp[i-1][j]);
                    }
                }

            }
        }
        return dp[s.length()][p.length()];
    }
}

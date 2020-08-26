package com.leetcode.Top1;

import java.util.HashSet;
import java.util.Set;

public class P3_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int ans = 0;

        int start = 0, end = 0;
        Set<Character> set = new HashSet<>();
        while (end < s.length()) {
            while (end < s.length() && !set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                end++;
                ans = Math.max(ans, end - start);
            }
            if (end == s.length()) break;
            while (start <= end && set.contains(s.charAt(end))) {
                set.remove(s.charAt(start));
                start++;
            }
        }
        return ans;
    }
}

package com.leetcode.Top3;

import java.util.*;

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

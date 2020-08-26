package com.leetcode.Top1;

import java.util.*;

public class P17_LetterCombinationsOfAPhoneNumber {

    public List<String> letterCombinations(String digits) {
        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        StringBuilder cur = new StringBuilder();
        dfs(digits, res, map, 0, cur);
        return res;
    }

    private void dfs(String digits, List<String> res, Map<Character,char[]> map, int i, StringBuilder s) {
        if (i >= digits.length()) {
            res.add(s.toString());
            return;
        }
        for (char c : map.get(digits.charAt(i))) {
            s.append(c);
            dfs(digits, res, map, i + 1, s);
            s.deleteCharAt(s.length() - 1);
        }
    }
}

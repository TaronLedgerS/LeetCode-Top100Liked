[TOC]

# LeetCode-Top1-1_10

-   Problems-ID: **1** 、2、3、4、5、10、11、15、17、19

## [EASY DONE]1. Two Sum

## [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)

### 题解（链表形-高精度加法）-2020年8月17日

```java
public class P2_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(-1);
        int carry = 0;
        ListNode cur = dummyHead;
        while (l1 != null || l2 != null) {
            int x = 0, y = 0;
            if (l1 != null) {
                x = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                y = l2.val;
                l2 = l2.next;
            }
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

}
```

## [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

### 题解（滑动窗口）-2020年8月18日

```java
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
```

## [4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)

### ▷题解（俩有序数组找中位数-二分）-2020年8月19日

```java
public class P4_MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) {
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i] ) {
                iMin = i + 1;
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1;
            } else {
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) return maxLeft;
                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
```

## [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

### ❤题解（dp或者枚举）-2020年8月20日

```java
public class P5_LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int start = 0;
        int end = 0;
        int max = 0;
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                }
                if(dp[j][i] && max < i - j + 1) {
                    max = i - j + 1;
                    start = j;
                    end = i;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
```

## [10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)

### 题解（DP）-2020年8月21日

```java
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
```

[每日一题：小Fu讲解LeetCode 10. Regular Expression Matching](https://www.bilibili.com/video/BV1tx411E722?from=search&seid=8800593481936910193)

## [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)

### 题解（贪心+双指针）-2020年8月22日

```java
public class P11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] > height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxarea;
    }
}
```

## [15. 3Sum](https://leetcode.com/problems/3sum/)

### 题解（双指针）-2020年8月23日

```java
public class P15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int k = 0; k < nums.length - 2; k++) {
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int target = -nums[k];
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    ArrayList<Integer> ans = new ArrayList<>();
                    ans.add(nums[k]);
                    ans.add(nums[i]);
                    ans.add(nums[j]);
                    result.add(ans);
                    i++;
                    while (i < j && nums[i] == nums[i - 1]) {
                        i++;
                    }
                    j--;
                    while (i < j && nums[j] == nums[j + 1]) {
                        j--;
                    }
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return result;
    }
}
```

## [17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)

### 题解（dfs-回溯）-2020年8月24日

```java
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
```

## [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

### 题解（快慢指针+DummyNode）-2020年8月25日

```java
public class P19_RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
```

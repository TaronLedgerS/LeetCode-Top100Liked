[TOC]

# LeetCode-Top11_20

-   Problems-ID: **20** 、 **21** 、22、23、32、33、34、39、41、42

## [EASY DONE]20. Valid Parentheses

## [EASY DONE]21. Merge Two Sorted Lists

## [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)

### 题解（dfs-回溯）-2020年8月26日

```java
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
```

## [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)

### 题解（堆）-2020年8月27日

```java
public class P23_MergekSortedLists {
    /*
        nklogk
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((ln1, ln2) -> ln1.val - ln2.val);
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }
        while (!pq.isEmpty()){
            ListNode next = pq.poll();
            cur.next = next;
            cur = cur.next;
            if (next.next != null) {
                pq.offer(next.next);
            }
        }
        return dummy.next;
    }
}
```

## [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/)

### 题解（栈存下标）-2020年8月28日

```java
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
```

## [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array)

### 题解（二分法）-2020年8月29日

```java
public class P33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[start] <= nums[mid]){
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }

            if (nums[mid] <= nums[end]){
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}
```

## [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

### 题解（二分）-2020年8月30日

```java
public class P34_FindFirstAndLastPositionofElementinSortedArray {
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }
        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;
        return targetRange;
    }
}
```

## [39. Combination Sum](https://leetcode.com/problems/combination-sum/)

### 题解（dfs回溯）-2020年8月31日

```java
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
```

## [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/)

### 题解（归位）-2020年9月1日

```java
public class P41_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        if(null==nums||len==0)return 1;
        //数据预处理 桶排序处理数据
        for(int i=0;i<len;){
            int cur = nums[i];
            //满足这四个条件才可以交换，交换就不++
            if(cur!=i+1&&cur<=len&&cur>0&&cur!=nums[cur-1]){
                //交换 i和cur-1
                nums[i] = nums[cur-1];
                nums[cur-1] = cur;
            }else{
                ++i;
            }
        }
        //找最小正数
        for(int i=0;i<len;++i){
            if(i+1!=nums[i]){
                return i+1;
            }
        }
        // 1 2 3 4这种情况
        return len+1;
    }
}
```

## [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

### 题解（暴力、前缀后缀数组、双指针）-2020年9月2日

```java
public class P42_TrappingRainWater {
    public int trap(int[] height) {
        int n = height.length;
        if (n==0) return 0;
        int[] maxl = new int[n];
        int[] maxr = new int[n];
        int sum = 0;
        for (int i = 0;i<n;i++)
            maxl[i] = i==0?height[i]:Math.max(maxl[i-1],height[i]);
        for (int i = n-1;i>=0;i--)
            maxr[i] = i==n-1?height[i]:Math.max(maxr[i+1],height[i]);
        for (int i = 0; i < n; i++) {
            sum += Math.min(maxl[i], maxr[i]) - height[i];
        }
        return sum;
    }
}
```

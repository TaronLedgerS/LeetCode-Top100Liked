package com.leetcode.Top1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

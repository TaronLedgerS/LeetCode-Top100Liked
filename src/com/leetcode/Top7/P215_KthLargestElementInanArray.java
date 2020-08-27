package com.leetcode.Top7;

public class P215_KthLargestElementInanArray {
    static void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    static void quick_select(int[] nums,int k,int start,int end){
        if(start>=end){
            return;
        }
        int pivot = nums[start];
        int l = start+1;
        int r = end;
        while(l<=r){
            if(nums[l]>pivot){// 从大到小排
                l++;
                continue;
            }
            if(nums[r]<=pivot){
                r--;
                continue;
            }
            swap(nums,l,r);
        }
        swap(nums,start,r);
        // 区别在这
        if(r-start+1==k){
            return;
        }
        if(r-start+1>k){
            quick_select(nums,k,start,r-1);
        }else{
            quick_select(nums,k-(l-start),l,end);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        quick_select(nums,k,0,nums.length-1);
        return nums[k-1];
    }
}

package com.leetcode.Top2;

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

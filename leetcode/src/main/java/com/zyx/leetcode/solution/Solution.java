package com.zyx.leetcode.solution;

/**
 * @author zhangyuxiao
 * @date 2021-07-20 09:58
 * @description 每日leetcode刷题
 */

//Definition for a binary tree node.


public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        if (nums1.length > nums2.length) {
//            int[] temp = nums1;
//            nums1 = nums2;
//            nums2 = temp;
//        }
        int m = nums1.length, n = nums2.length;
        int numsOfLeft = (n + m + 1) / 2;
        int left = 0, right = m;
        while (left < right) {
            int i = left + (right - left + 1) / 2;
            int j = numsOfLeft - i;
            if (nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
        }
        int i = left, j = numsOfLeft - i;
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];
        if ((m + n) % 2 == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        }
        return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        //final com.zyx.leetcode.utils.TreeNode root = com.zyx.leetcode.utils.TreeNode.buildTree(new Integer[]{2, 2, 5, null, null, 5, 7});
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
}

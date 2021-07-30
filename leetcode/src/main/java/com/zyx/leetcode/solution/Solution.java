package com.zyx.leetcode.solution;

/**
 * @author zhangyuxiao
 * @date 2021-07-20 09:58
 * @description 每日leetcode刷题
 */

//Definition for a binary tree node.


public class Solution {
    private static final int[] powers = {1, 26, 676, 17576, 456976, 11881376, 308915776};
    public int titleToNumber(String columnTitle) {
        int len = columnTitle.length(), ans = 0;
        for (int i = 0; i < len; i++) {
            ans += (columnTitle.charAt(i) - 'A' + 1) * (powers[len - i - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i <= 6; i++) {
            ans.append((int) Math.pow(26, i));
            ans.append(", ");
        }
        System.out.println(ans);
    }
}

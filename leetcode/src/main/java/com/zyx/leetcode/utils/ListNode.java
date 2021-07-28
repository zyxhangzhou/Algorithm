package com.zyx.leetcode.utils;

/**
 * @author zhangyuxiao
 * @date 2021-07-27 19:02
 * @description
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode buildList(Integer[] arr) {
        return build(arr, 0);
    }

    public static ListNode build(Integer[] arr, int index) {
        if (index >= arr.length) return null;
        ListNode root = new ListNode(arr[index]);
        root.next = build(arr, index + 1);
        return root;
    }
}


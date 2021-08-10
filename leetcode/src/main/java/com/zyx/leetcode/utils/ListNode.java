package com.zyx.leetcode.utils;

/**
 * @author zhangyuxiao
 * @date 2021-07-27 19:02
 * @description
 */
public class ListNode {
    public int val;
    public ListNode next;


    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
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

    public static ListNode stringArray2ListNode(String s) {
        return buildList(ParamsUtils.array2Integers(s));
    }

    @Override
    public String toString() {
        return val + " " + (next == null ? "" : next.toString());
    }

//    public static void main(String[] args) {
//        Integer[] arr = new Integer[]{1, 2, 3, 4};
//        ListNode listNode = buildList(arr);
//        System.out.println(listNode);
//    }
}


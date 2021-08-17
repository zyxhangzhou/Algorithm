package com.zyx.leetcode.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author zhangyuxiao
 * @date 2021-07-27 19:02
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListNode) {
            final ListNode node = (ListNode) obj;
            return this.val == node.val && Objects.equals(this.next, node.next);
        }
        return false;
    }
}


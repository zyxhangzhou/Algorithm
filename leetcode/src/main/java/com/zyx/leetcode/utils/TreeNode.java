package com.zyx.leetcode.utils;

/**
 * @author zhangyuxiao
 * @date 2021-07-27 10:27
 * @description 🌲的结点
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0) return null;
        return build(arr, 0);
    }

    public static TreeNode build(Integer[] arr, int index) {
        if (index >= arr.length || arr[index] == null) {
            return null;
        }
        TreeNode root = new TreeNode(arr[index]);
        root.left = build(arr, index * 2 + 1);
        root.right = build(arr, index * 2 + 2);
        return root;
    }
}

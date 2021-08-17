package com.zyx.leetcode.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayDeque;
import java.util.Objects;

/**
 * @author zhangyuxiao
 * @date 2021-07-27 10:27
 * @description 🌲的结点
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int val) {
        this.val = val;
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

    /**
     * 递归前序
     *
     * @param root 根
     */
    public static void frontTraversal(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        frontTraversal(root.left);
        frontTraversal(root.right);
    }

    /**
     * 非递归前序
     *
     * @param root 根
     */
    public static void frontTraversalNonRecursive(TreeNode root) {
        final ArrayDeque<TreeNode> stk = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stk.isEmpty()) {
            while (node != null) {
                System.out.print(node.val + " ");
                stk.addLast(node);
                node = node.left;
            }
            if (!stk.isEmpty()) {
                node = stk.pollLast();
                node = node.right;
            }
        }
    }

    /**
     * 递归中序
     *
     * @param root 根
     */
    public static void middleTraversal(TreeNode root) {
        if (root == null) return;
        middleTraversal(root.left);
        System.out.print(root.val + " ");
        middleTraversal(root.right);
    }

    /**
     * 非递归中序
     *
     * @param root 根
     */
    public static void middleTraversalNonRecursive(TreeNode root) {
        final ArrayDeque<TreeNode> stk = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stk.isEmpty()) {
            while (node != null) {
                stk.addLast(node);
                node = node.left;
            }
            if (!stk.isEmpty()) {
                node = stk.pollLast();
                System.out.print(node.val + " ");
                node = node.right;
            }
        }
    }

    /**
     * 递归后序
     *
     * @param root 根
     */
    public static void backTraversal(TreeNode root) {
        if (root == null) return;
        backTraversal(root.left);
        backTraversal(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * 非递归后序
     *
     * @param root 根
     */
    public static void backTraversalNonRecursive(TreeNode root) {
        final ArrayDeque<TreeNode> stk = new ArrayDeque<>();
        TreeNode cur, pre = null;
        stk.addLast(root);
        while (!stk.isEmpty()) {
            cur = stk.getLast();
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                System.out.print(cur.val + " ");
                pre = stk.pollLast();
            } else {
                // 右边先进栈
                if (cur.right != null)
                    stk.addLast(cur.right);
                if (cur.left != null) {
                    stk.addLast(cur.left);
                }
            }
        }
    }

    /**
     * 前序+中序 -> 后序
     *
     * @param front  前序数组
     * @param middle 中序数组
     * @return treeNode
     */
    public static TreeNode frontMid2back(Integer[] front, Integer[] middle) {
        if (front.length != middle.length) {
            System.out.println("illegal input!");
            return null;
        }
        return frontMid2backHelper(0, 0, front.length - 1, front, middle);
    }

    public static TreeNode frontMid2backHelper(int rootIndex, int start, int end, Integer[] front, Integer[] middle) {
        if (start > end) return null;
        int rootValue = front[rootIndex], midIndex = start;
        while (middle[midIndex] != rootValue) midIndex++;
        final TreeNode root = new TreeNode(rootValue);
        root.left = frontMid2backHelper(rootIndex + 1, start, midIndex - 1, front, middle);
        root.right = frontMid2backHelper(rootIndex + midIndex - start + 1, midIndex + 1, end, front, middle);
        return root;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, 4};
        final TreeNode root = buildTree(arr);
        Integer[] front = new Integer[]{1, 2, 4, 5, 3};
        Integer[] middle = new Integer[]{4, 2, 5, 1, 3};
        final TreeNode root2 = frontMid2back(front, middle);
        System.out.println(Objects.equals(root, root2));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TreeNode) {
            final TreeNode root = (TreeNode) obj;
            final boolean equal = this.val == root.val;
            return equal && Objects.equals(this.left, root.left) && Objects.equals(this.right, root.right);
        }
        return false;
    }
}

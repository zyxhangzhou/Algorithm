package com.zyx.leetcode.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
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
    private static final int MAX_SIZE = 1024;
    private static int nowSize = 0;
    private static final Integer[] level = new Integer[MAX_SIZE];

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
        final Deque<TreeNode> stk = new ArrayDeque<>();
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
     * leetCode风格的层序遍历
     *
     * @param root 根
     */
    public static void levelTraversal(TreeNode root) {
        if (root == null) return;
        final Deque<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.offerFirst(Pair.of(root, 0));
        while (!queue.isEmpty()) {
            final Pair<TreeNode, Integer> poll = queue.pollLast();
            final TreeNode node = poll.getLeft();
            final Integer nowIdx = poll.getRight();
            if (node == null) continue;
            nowSize = nowIdx;
            level[nowIdx] = node.val;
            queue.offerFirst(Pair.of(node.left, nowIdx * 2 + 1));
            queue.offerFirst(Pair.of(node.right, nowIdx * 2 + 2));

        }
        levelTraversalPrinter();
    }

    /**
     * 根据数组打印层序数组
     */
    public static void levelTraversalPrinter() {
        System.out.print('[');
        for (int i = 0; i < MAX_SIZE && i <= nowSize; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(level[i]);
        }
        System.out.println(']');
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
    public static TreeNode frontMid2Tree(Integer[] front, Integer[] middle) {
        if (front.length != middle.length) {
            System.out.println("illegal input!");
            return null;
        }
        return frontMid2TreeHelper(0, 0, front.length - 1, front, middle);
    }

    public static TreeNode frontMid2TreeHelper(int rootIndex, int start, int end, Integer[] front, Integer[] middle) {
        if (start > end) return null;
        int rootValue = front[rootIndex], midIndex = start;
        while (middle[midIndex] != rootValue) midIndex++;
        final TreeNode root = new TreeNode(rootValue);
        root.left = frontMid2TreeHelper(rootIndex + 1, start, midIndex - 1, front, middle);
        root.right = frontMid2TreeHelper(rootIndex + midIndex - start + 1, midIndex + 1, end, front, middle);
        return root;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, null, null, 4, 5};
        final TreeNode root = buildTree(arr);
        Integer[] front = new Integer[]{1, 2, 4, 5, 3};
        Integer[] middle = new Integer[]{4, 2, 5, 1, 3};
        final TreeNode root2 = frontMid2Tree(front, middle);
        System.out.println(Objects.equals(root, root2));
        levelTraversal(root);
    }

    /**
     * 重写判断两🌲是否相等
     *
     * @param obj Object
     * @return 布尔值
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TreeNode) {
            final TreeNode root = (TreeNode) obj;
            return this.val == root.val && Objects.equals(this.left, root.left) && Objects.equals(this.right, root.right);
        }
        return false;
    }
}

package com.zyx.leetcode.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

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
    private static int nowSize = 0;
    private static final List<Integer> levels = new ArrayList<>();
    private static final Map<Integer, Integer> idx2Num = new HashMap<>();

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 第n层第最右边第index（层数从1算起）
     *
     * @param depth 层数
     * @return index
     */
    public static int levelRange(int depth) {
        if (depth == 1) return 0;
        return (int) Math.pow(2, depth) - 2;
    }

    /**
     * 构建完美二叉树的index与arr的关系
     *
     * @param arr 入参
     */
    public static void buildIdxMap(Integer[] arr) {
        int depth = 1, theFirstNonNullAbove = 0;
        for (int i = 0; i < arr.length; ) {
            int right = levelRange(depth);
            boolean flag = true;
            for (int j = theFirstNonNullAbove; j <= right && i < arr.length; j++, i++) {
                idx2Num.put(j, arr[i]);
                if (arr[i] != null && flag) {
                    theFirstNonNullAbove = j;
                    flag = false;
                }
            }
            theFirstNonNullAbove = theFirstNonNullAbove * 2 + 1;
            depth++;
        }
    }

    /**
     * 构造🌲
     *
     * @param arr 数组
     * @return 🌲节点
     */
    public static TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0) return null;
        buildIdxMap(arr);
        return build(0);
    }

    /**
     * 构造🌲的主体
     * 从map中获取index
     *
     * @param index 位置
     * @return 🌲节点
     */
    public static TreeNode build(int index) {
        if (!idx2Num.containsKey(index) || idx2Num.get(index) == null) {
            return null;
        }
        return new TreeNode(idx2Num.get(index), build(2 * index + 1), build(2 * index + 2));
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
        final Deque<TreeNode> stk = new ArrayDeque<>();
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
        final Queue<TreeNode> queue = new LinkedList<>();
        levels.clear();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            levels.add(node == null ? null : node.val);
            if (node == null) continue;
            nowSize = levels.size();
            queue.offer(node.left);
            queue.offer(node.right);
        }
    }

    /**
     * 根据数组打印层序数组
     */
    public static void levelTraversalPrinter(TreeNode root) {
        levelTraversal(root);
        System.out.print('[');
        for (int i = 0; i < nowSize && i < levels.size(); i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(levels.get(i));
        }
        System.out.println(']');
    }

    /**
     * 非递归后序
     *
     * @param root 根
     */
    public static void backTraversalNonRecursive(TreeNode root) {
        final Deque<TreeNode> stk = new ArrayDeque<>();
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
        Integer[] arr = new Integer[]{1, null, 2, null, 3};
        TreeNode treeNode = buildTree(arr);
        levelTraversalPrinter(treeNode);
        frontTraversal(treeNode);
        middleTraversal(treeNode);
        System.out.println(treeNode);
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

    @Override
    public String toString() {
        levelTraversal(this);
        final StringBuffer buffer = new StringBuffer();
        buffer.append('[');
        for (int i = 0; i < nowSize && i < levels.size(); i++) {
            if (i > 0) buffer.append(", ");
            buffer.append(levels.get(i));
        }
        buffer.append("]\n");
        return buffer.toString();
    }
}

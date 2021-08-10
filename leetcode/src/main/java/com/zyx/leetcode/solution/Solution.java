package com.zyx.leetcode.solution;

import com.zyx.leetcode.utils.ListNode;

/**
 * @author zhangyuxiao
 * @date 2021-07-20 09:58
 * @description 每日leetcode刷题
 */

//Definition for a binary tree node.


public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode fastL1 = new ListNode(l1.val, l1.next);
        ListNode fastL2 = new ListNode(l2.val, l2.next);
        ListNode slowLongList = l1, slowShortList = l2, fastLongList;
        for (; fastL1 != null && fastL2 != null; fastL1 = fastL1.next, fastL2 = fastL2.next) ;
        if (fastL1 == null && fastL2 == null) {
            if (addNum(l1, l2) > 0) {
                return new ListNode(1, l1);
            }
            return l1;
        } else if (fastL1 == null) {
            slowLongList = l2;
            slowShortList = l1;
            fastLongList = fastL2;
        } else {
            fastLongList = fastL1;
        }
        int len = 0;
        ListNode copyOfSlowLongList = slowLongList;
        for (; fastLongList != null; slowLongList = slowLongList.next, fastLongList = fastLongList.next, ++len) ;

        // 得到差值补全链表
        // 9 9 9 9 9
        //     9 9 9 +
        // ->补全为 0 0 9 9 9
        final ListNode[] headAndTail = buildZeroList(len);
        headAndTail[1].next = slowShortList;
        if (addNum(copyOfSlowLongList, headAndTail[0]) > 0) {
            return new ListNode(1, copyOfSlowLongList);
        }
        return copyOfSlowLongList;
    }

    private static ListNode[] buildZeroList(int len) {
        ListNode head = new ListNode(0);
        ListNode dummy = new ListNode(-1, head);
        for (int i = 1; i < len; i++) {
            head.next = new ListNode(0);
            head = head.next;
        }
        return new ListNode[]{dummy.next, head};
    }

    private static int addNum(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return 0;
        int num = l1.val + l2.val + addNum(l1.next, l2.next);
        l1.val = num % 10;
        return num / 10;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.addTwoNumbers(ListNode.stringArray2ListNode("[9,9,9,9]"), ListNode.stringArray2ListNode("[9,9,9,9,9,9,9]")));
    }
}
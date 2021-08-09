package com.zyx.leetcode.solution;

import com.zyx.leetcode.utils.ListNode;
import com.zyx.leetcode.utils.ParamsUtils;

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
        ListNode fastLongList = null, slowLongList = null, headShortList = null, headLongList = null;
        int carry = 0;
        for (; fastL1 != null && fastL2 != null; fastL1 = fastL1.next, fastL2 = fastL2.next) ;
        if (fastL1 == null && fastL2 == null) {
            carry = addNums(l1, l2);
            if (carry > 0) {
                return new ListNode(1, l1);
            }
            return l1;
        } else if (fastL1 == null) {
            fastLongList = fastL2;
            slowLongList = new ListNode(l2.val, l2.next);
            headShortList = l1;
            headLongList = l2;
        } else {
            fastLongList = fastL1;
            slowLongList = new ListNode(l1.val, l1.next);
            headShortList = l2;
            headLongList = l1;
        }
        int len = 0;
        for (; fastLongList != null; fastLongList = fastLongList.next, slowLongList = slowLongList.next, len++) ;
        final ListNode[] listNodes = buildZeroList(len);
        listNodes[1].next = headShortList;
        carry = addNums(headLongList, listNodes[0]);
        if (carry > 0) {
            return new ListNode(1, headLongList);
        }
        return headLongList;
    }

    public static int addNums(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return 0;
        }
        final int num = l1.val + l2.val + addNums(l1.next, l2.next);
        l1.val = num % 10;
        return num / 10;
    }

    public static ListNode[] buildZeroList(int len) {
        ListNode dummy = new ListNode(-1);
        ListNode head = new ListNode(0);
        dummy.next = head;
        for (int i = 1; i < len; i++) {
            head.next = new ListNode(0);
            head = head.next;
        }
        return new ListNode[]{dummy.next, head};
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final Integer[] integers1 = ParamsUtils.array2Integers("[2,4,3]");
        final Integer[] integers2 = ParamsUtils.array2Integers("[5,6,4]");

        System.out.println(solution.addTwoNumbers(ListNode.buildList(integers1), ListNode.buildList(integers2)));

    }
}
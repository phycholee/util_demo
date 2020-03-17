package com.llf.demo.java.leetcode.nodeSum;

/**
 * @author: Oliver.li
 * @Description: 两数相加
 * @date: 2020/1/9 19:37
 */
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultNode = new ListNode(0);
        ListNode currentNode = resultNode;

        ListNode next1 = l1;
        ListNode next2 = l2;

        int carry = 0;
        do {
            int val1 = 0;
            int val2 = 0;
            if (next1 != null) {
                val1 = next1.val;
                next1 = next1.next;
            }
            if (next2 != null) {
                val2 = next2.val;
                next2 = next2.next;
            }

            int result = val1 + val2 + carry;
            carry = result / 10;

            currentNode.next = new ListNode(result % 10);
            currentNode = currentNode.next;
        } while (next1 != null || next2 != null);

        if (carry > 0){
            currentNode.next = new ListNode(carry);
        }

        return resultNode.next;
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(9);

        ListNode l2 = new ListNode(1);
        ListNode l21 = new ListNode(9);
        ListNode l22 = new ListNode(9);
        ListNode l23 = new ListNode(9);
        ListNode l24 = new ListNode(9);
        ListNode l25 = new ListNode(9);
        ListNode l26 = new ListNode(9);
        ListNode l27 = new ListNode(9);
        ListNode l28 = new ListNode(9);
        ListNode l29 = new ListNode(9);
        l2.next = l21;
        l21.next = l22;
        l22.next = l23;
        l23.next = l24;
        l24.next = l25;
        l25.next = l26;
        l26.next = l27;
        l27.next = l28;
        l28.next = l29;

        ListNode listNode = new Solution().addTwoNumbers(l1, l2);
        ListNode next = listNode;
        do {
            int val = next.val;
            System.out.println(val);
            next = next.next;
        } while (next != null);
    }

}

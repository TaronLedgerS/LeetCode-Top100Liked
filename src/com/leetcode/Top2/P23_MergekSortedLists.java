package com.leetcode.Top2;

import java.util.PriorityQueue;

public class P23_MergekSortedLists {
    /*
        nklogk
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((ln1, ln2) -> ln1.val - ln2.val);
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }
        while (!pq.isEmpty()){
            ListNode next = pq.poll();
            cur.next = next;
            cur = cur.next;
            if (next.next != null) {
                pq.offer(next.next);
            }
        }
        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val,ListNode next) {
        this.val = val;
        this.next = next;
    }
}
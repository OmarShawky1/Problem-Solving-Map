package linkedList;

import java.util.PriorityQueue;

public class MergeKLists {

    // Most Maintainable Code
    private final PriorityQueue<Integer> pq = new PriorityQueue<>();
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = new ListNode(0); // Dummy val

        // Displace all elements inside priority Queue
        for (ListNode ln : lists)
            for (; ln != null; ln = ln.next) pq.add(ln.val);


        // Pop priority Queue in a listNode
        for (ListNode cur = res; !pq.isEmpty(); cur = cur.next) cur.next = new ListNode(pq.poll());

        return res.next;
    }

    // Optimal Solution on LC
    // Merge sort Algorithm
    public ListNode mergeKLists1(ListNode[] lists) {
        // Base case, unnecessary check
        if (lists == null || lists.length == 0) return null;

        return sort(lists, 0, lists.length - 1);
    }

    // Sort using Divide and Conquer, you can rename function "divideAndConquer"
    private ListNode sort(ListNode[] lists, int lo, int hi) {
        // Invalid; Reached smallest arrays possible
        if (lo > hi) return null;

        // Base Case; size = 1
        if (lo == hi) return lists[lo];

        int mid = lo + (hi - lo) / 2;
        ListNode left = sort(lists, lo, mid);
        ListNode right = sort(lists, mid + 1, hi);
        return mergeList(left, right);
    }

    private ListNode mergeList(ListNode n1, ListNode n2) {
        ListNode mergedList = new ListNode(-1); // Dummy head
        ListNode prev = mergedList;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                prev.next = n1;
                n1 = n1.next;
            } else {
                prev.next = n2;
                n2 = n2.next;
            }
            prev = prev.next;
        }

        // Add the remaining list (one of both at most is still not null from above condition)
        prev.next = (n1 != null) ? n1 : n2;

        return mergedList.next;
    }

    public static void test() {
        MergeKLists m = new MergeKLists();
        
    }
}

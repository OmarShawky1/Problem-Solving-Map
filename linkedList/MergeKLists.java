package linkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKLists {

    // Most Maintainable Code
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = new ListNode(0); // Dummy val
        // Displace all elements inside priority Queue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (ListNode ln : lists)
            for (; ln != null; ln = ln.next) pq.add(ln.val);

        // Pop priority Queue in a listNode
        for (ListNode cur = res; !pq.isEmpty(); cur = cur.next) cur.next = new ListNode(pq.poll());

        return res.next;
    }

    // Maintainable Code with enhanced asymptotic algorithmic complexity but slower on LC because of cache pessimization
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode res = new ListNode(0); // Dummy val
        // Displace first element of each list
        // Make PQ consume Integer comparator (receives integer and gives boolean) with parameter "node" and compare val
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        for (ListNode ln : lists) if (ln != null) pq.add(ln);

        // Actively pop and push from and to PQ
        // Continue until all nodes are processed
        ListNode cur = res;
        while (!pq.isEmpty()) {
            // Get the node with the smallest value from the priority queue
            ListNode node = pq.poll();
            // Add this node to the result list
            cur.next = node;
            // Move the node pointer to the next node in its list
            if (node.next != null) pq.add(node.next);
            // Move the current pointer to the added node
            cur = cur.next;
        }
        return res.next;
    }

    // Optimal Solution on LC
    // Merge sort Algorithm
    public ListNode mergeKLists2(ListNode[] lists) {
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
        ListNode curr = mergedList;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                curr.next = n1;
                n1 = n1.next;
            } else {
                curr.next = n2;
                n2 = n2.next;
            }
            curr = curr.next;
        }

        // Add the remaining list (one of both at most is still not null from above condition)
        curr.next = (n1 != null) ? n1 : n2;

        return mergedList.next;
    }

    public static void test() {
        MergeKLists m = new MergeKLists();

    }
}

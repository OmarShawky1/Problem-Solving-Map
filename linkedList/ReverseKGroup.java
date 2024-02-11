package linkedList;

public class ReverseKGroup {
    // Most Maintainable & Optimal Solution
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode newH = new ListNode(0); // Dummy

        newH.next = head;
        ListNode start = newH; // mini head start, changes each time after k elements
        for (int count = 0; head != null; count++) {
            if (count % k == 0) {
                start = reverse(start, head.next); // return new head of the flipped mini list
                head = start.next; // Move to next step as start.next is now the next step
            } else head = head.next;
        }

        return newH.next;
    }

    private ListNode reverse(ListNode start, ListNode next) {
        // Make end node. Start at the beginning of the list (which is start.next, not start as start is before list)
        ListNode end = start.next;
        // store node after end which will be investigated down
        ListNode curr = end.next;
        // While current node 'curr' is not at the end of the list (before next), reverse it back in the list
        while (curr != next) {
            // Store next; Make end point to the next item
            end.next = curr.next;
            // Make current node point to the beginning of the list
            curr.next = start.next;
            // Make current node the start of the list; Make start point to current
            start.next = curr;
            // Move current to previous stored next
            curr = end.next;
        }
        return end;
    }

    public static void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        int k = 3;
        ReverseKGroup r = new ReverseKGroup();
        ListNode res = r.reverseKGroup(head, k);

        // Expected Output: 2 -> 1 -> 4 -> 3 -> 5
        while (res != null) {
            System.out.print(res.val);
            res = res.next;
        }
    }
}

package linkedList;

public class RemoveNthFromEnd {

    // Maintainable
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // Get size
        int size = 0;
        for (ListNode curr = head; curr != null; curr = curr.next) size++;

        // Get index to remove
        int indToDelete = size - n - 1;

        // If size is same as nth to be removed, means remove head (head = head.next).
        if (size == n) return head.next;

        // Otherwise, Get node before one we want to delete
        ListNode curr = head;
        for (int i = 0; i < indToDelete; i++) curr = curr.next;

        // Remove node
        ListNode nodeToDelete = curr.next;
        ListNode nextOne = nodeToDelete == null? null : nodeToDelete.next;
        curr.next = nextOne;

        return head;
    }

    // Alternative solution
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null || n <= 0)
                return null;

            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode fast = dummy;
            ListNode slow = dummy;

            // Move the fast pointer n+1 steps ahead
            for (int i = 0; i <= n; i++) {
                if (fast == null)
                    return null; // n is greater than the length of the list
                fast = fast.next;
            }

            // Move both pointers until fast reaches the end
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            // Remove the nth node from the end
            slow.next = slow.next.next;

            return dummy.next;
        }
    }

    public static void test() {

    }
}

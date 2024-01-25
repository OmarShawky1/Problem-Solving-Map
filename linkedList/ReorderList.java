package linkedList;

public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // Find middle
        ListNode middle = findMiddle(head);

        // reverse after middle
        ListNode reversedMiddleHalf = reverseList(middle.next);
        middle.next = null;

        // Merge two halves
        mergeLists(head, reversedMiddleHalf);
    }

    private void mergeLists(ListNode l1, ListNode l2) {
        while (l2 != null) {
            ListNode nextL1 = l1.next;
            ListNode nextL2 = l2.next;

            l1.next = l2;
            l2.next = nextL1;

            l1 = nextL1;
            l2 = nextL2;
        }
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null, current = head;

        while (current != null) {
            // Store
            ListNode next = current.next;

            // Reverse
            current.next = prev;
            prev = current;

            current = next; // next
        }
        return prev;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private class ListNode {
        private int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void test() {

    }
}

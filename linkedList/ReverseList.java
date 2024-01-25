package linkedList;

public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next; // store

            //reverse
            curr.next = prev;
            prev = curr;

            curr = next; // next
        }

        return prev;
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

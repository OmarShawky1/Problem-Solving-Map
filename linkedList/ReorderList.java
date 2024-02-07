package linkedList;

public class ReorderList {

    // Most Maintainable
    // Hare Tortoise Algorithm
    public void reorderList(ListNode head) {
        // Unnecessary check, can be removed
        if (head == null || head.next == null) return;
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

        // fast.next.next to reach element before middle as to make it point to null
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

    // Back tracking solution: https://chat.openai.com/share/d4bdc04e-49ce-4f37-951d-b7fc056faba8
    // Fastest
    class Solution {
        public static void reorderList(ListNode head) {
            if (head.next == null) {
                return;
            }
            reorderList2(head, head.next);
        }

        public static ListNode reorderList2(ListNode head, ListNode curr) {
            ListNode temp;
            if (curr.next != null) {
                temp = reorderList2(head, curr.next);
            } else {
                temp = head;
            }
            if(temp == null) {
                return null;
            }
            if (temp == curr  || temp.next == curr) {
                curr.next=null;
                return null;
            }

            curr.next = temp.next;
            temp.next = curr;
            return curr.next;
        }
    }

    public static void test() {

    }
}

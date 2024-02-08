package linkedList;

public class AddTwoNumbers {

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0); // Create a dummy node, return its next
        ListNode currHead = head;
        int carry = 0;

        // If there is still any number in l1, l2 or carry, continue adding
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry; // Use carry

            if (l1 != null && l2 != null) sum += l1.val + l2.val;
            else if (l1 != null) sum += l1.val;
            else if (l2 != null) sum += l2.val;

            //Erase carry after using it
            carry = 0;

            //Create new carry
            if (sum > 9) {
                sum -= 10;
                carry = 1;
            }

            currHead.next = new ListNode(sum);
            currHead = currHead.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return head.next;
    }

    // Same but more compacted
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int l1Val = (l1 != null) ? l1.val : 0;
            int l2Val = (l2 != null) ? l2.val : 0;
            carry += l1Val + l2Val;

            curr.next = new ListNode(carry % 10);

            carry /= 10;
            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummyHead.next;
    }

    public static void test() {

    }
}

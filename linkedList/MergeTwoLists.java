package linkedList;

public class MergeTwoLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newH = new ListNode(0); // dummy node that we will return its next. removes redundant checks
        ListNode current = newH;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // If one of the lists is not empty, append the remaining nodes
        if (list1 != null) current.next = list1;
        else current.next = list2;
        return newH.next;
    }

    private class ListNode {
        private int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        /*ListNode(int[] list, int i) {
            if (i < list.length) {
              val = list[i];
              next = new ListNode(list, i + 1);
            }
        }*/
    }

    public static void test() {
        MergeTwoLists m = new MergeTwoLists();
    }
}

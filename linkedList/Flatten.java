package linkedList;

/**
 * Make children be next of current node; put entire list in between current and current.next
 * Problem Link: <a href="https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/">Flatten a Multilevel Doubly Linked List</a>
 */
public class Flatten {
    // 100% time & space on LC
    public Node flatten(Node head) {
        if (head == null) return null; // Base case

        if (head.child != null) { // If node has child, make it node.next and node.next.prev = last node.child list node
            Node tempHeadNext = head.next; // store node.next to be used when pointing to node.child last list node

            // child is now next
            head.next = head.child;
            head.child.prev = head;
            head.child = null;

            // make old node.child last node and old head.next point to each other
            Node cur = head.next;
            while (cur.next != null) cur = cur.next;
            cur.next = tempHeadNext;
            if (tempHeadNext != null) tempHeadNext.prev = cur; // old next's prev is now child's list end
        }
        head.next = flatten(head.next);
        return head;
    }
    
    private class Node {
        private int val;
        private Node prev;
        private Node next;
        private Node child;

        public Node(int val, Node prev, Node next, Node child) {
            this.val = val;
            this.prev = prev;
            this.next = next;
            this.child = child;
        }
    }

    public void test() {
        Flatten f = new Flatten();

        /*
        // Base case, head = null
        Node n1 = null;
        assert f.flatten(n1) == null;

        // case head is a single node
        n1 = new Node(1, null, null, null);
        assert f.flatten(n1) == n1;

        // case head next but no child
        Node n2 = new Node(2, n1, null, null);
        n1.next = n2;
        n1 = flatten(n1);

        Node cur = n1;
        int cnt = 1;
        while (cur.next != null) {
            assert cur.val == cnt;
            cur = cur.next;
            cnt++;
        }

        // case head has child but not next
        n2 = new Node(2, null, null, null);
        n1 = new Node(1, null, null, n2);
        cur = n1;
        cnt = 1;
        while (cur.next != null) {
            assert cur.val == cnt;
            cur = cur.next;
            cnt++;
        }

        // case head has single child and next
        n2 = new Node(2, null, null, null);
        Node n3 = new Node(3, n1, null, null);
        n1 = new Node(1, null, n3, n2);
        n1 = f.flatten(n1);

        cur = n1;
        cnt = 1;
        while (cur.next != null) {
            assert cur.val == cnt;
            cur = cur.next;
            cnt++;
        }

        // case head has single child and next
        Node n4, n5;
        n2 = new Node(2, null, n3, null);
        n3 = new Node(3, n2, null, null);
        n4 = new Node(4, n1, null, null);
        n5 = new Node(5, n4, null, null);
        n4.child = n5;
        n1 = new Node(1, null, n4, n2);
        f.flatten(n1);

        cur = n1;
        cnt = 1;
        while (cur.next != null) {
            assert cur.val == cnt;
            cur = cur.next;
            cnt++;
        }*/

        Node n1 = new Node(1, null, null, null);

        Node n2 = new Node(2, n1, null, null);
        n1.next = n2;

        Node n3 = new Node(3, n2, null, null);
        n2.next = n3;

        Node n4 = new Node(4, n3, null, null);
        n3.next = n4;

        Node n5 = new Node(5, n4, null, null);
        n4.next = n5;

        Node n6 = new Node(6, n5, null, null);
        n5.next = n6;

        Node n7 = new Node(7, null, null, null);
        n3.child = n7;

        Node n8 = new Node(8, n7, null, null);
        n7.next = n8;

        Node n9 = new Node(9, n8, null, null);
        n8.next = n9;

        Node n10 = new Node(10, n9, null, null);
        n9.next = n10;

        Node n11 = new Node(11, null, null, null);
        n8.child = n11;

        Node n12 = new Node(12, n11, null, null);
        n11.next = n12;

        f.flatten(n1);
    }
}

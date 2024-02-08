package linkedList;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {
    // Not optimal
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // Index all nodes
        HashMap<Node, Integer> oldMap = new HashMap<>();
        Node oldHead = head;
        int oldIndex = 0;
        while (oldHead != null) {
            oldMap.put(oldHead, oldIndex);
            oldIndex++;
            oldHead = oldHead.next;
        }

        // Copy and Index all nodes
        oldHead = head; // reset head
        HashMap<Integer, Node> newMap = new HashMap<>();
        int newIndex = 0;

        // Add first node which has no previous
        Node newHead = new Node(oldHead.val);
        newMap.put(newIndex, newHead);

        // Next
        newIndex++;
        Node prevHead = newHead;
        oldHead = oldHead.next;
        while (oldHead != null) {
            Node newNode = new Node(oldHead.val);
            prevHead.next = newNode;
            newMap.put(newIndex, newNode);

            // Next
            newIndex++;
            prevHead = newNode;
            oldHead = oldHead.next;
        }

        // Copy all random pointers
        Node currNewHead = newHead;
        oldHead = head;
        while (currNewHead != null) {
            newMap.get(oldMap.get(oldHead)).random = newMap.get(oldMap.get(oldHead.random));
            currNewHead = currNewHead.next;
            oldHead = oldHead.next;
        }

        return newHead;
    }

    // Most Optimal & Maintainable
    public Node copyRandomList1(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();

        // First pass: create a copy of each node without assigning random pointer
        Node current = head;
        while (current != null) {
            map.put(current, new Node(current.val));
            current = current.next;
        }

        // Second pass: assign next and random pointers for the copy list
        current = head;
        while (current != null) {
            map.get(current).next = map.get(current.next);
            map.get(current).random = map.get(current.random);
            current = current.next;
        }

        // Return the head of the copy list
        return map.get(head);
    }

    public static void test() {

    }
}

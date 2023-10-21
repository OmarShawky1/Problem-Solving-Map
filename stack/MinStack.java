package stack;

/**
 * Create a Min Stack.
 * Problem Link: https://leetcode.com/problems/min-stack
 */
public class MinStack {
    Node head;
    int size;

    public MinStack() {
        head = null;
        size = 0;
    }

    public void push(int val) {
        if (head != null) head = new Node(head, val, Math.min(val, head.min));
        else head = new Node(null, val, val);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        private Node next;
        private int val, min;

        public Node(Node next, int val, int min) {
            this.val = val;
            this.next = next;
            this.min = min;
        }
    }

    public static void test() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin(); // return -3
        minStack.pop();
        minStack.top();    // return 0
        minStack.getMin(); // return -2
    }
}

package linkedList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    private final int CAPACITY;
    private int size;
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        head = new Node(-1, -1); // Dumy values
        tail = new Node(-1, -1); // Dumy values
        head.next = tail;
        tail.prev = head;

        map = new HashMap<>();
        this.CAPACITY = capacity;
        size = 0;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            // Get Node;
            Node node = map.get(key);
            // Delete it from the current position
            deleteNode(node);
            // Put it at the head of the list
            addToHead(node);
            map.put(key, head.next);
            return node.value;
        }
        return -1;
    }

    private void addToHead(Node node) {
        // Next of new node is the first of the old list.
        node.next = head.next;
         // And the first of the old list prev is the new first
        node.next.prev = node;
        // Then, the previous of new first node is the old dummy head
        node.prev = head;
        // Finally, head points to the new first.
        head.next = node;
    }

    private void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void put(int key, int value) {
        // If I already have this value
        if (get(key) != -1) head.next.value = value; // just find it then swap the value
        else {
            // Check before adding if we reached the maximum capacity
            if (size == CAPACITY) {
                // Remove from the map
                map.remove(tail.prev.key);
                // Remove from the tail
                deleteNode(tail.prev);
                size--;
            }
            // otherwise, add to head and map
            Node node = new Node(key, value);
            addToHead(node);
            map.put(key, node);
            size++;
        }
    }

    private class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void test() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        assert lRUCache.get(1) == 1;    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        assert lRUCache.get(2) == -1;    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        assert lRUCache.get(1) == -1;    // return -1 (not found)
        assert lRUCache.get(3) == 3;    // return 3
        assert lRUCache.get(4) == 4;    // return 4
    }
}

// Optimal & Most Maintainable
public class LRUCache {
    private final LinkedHashMap<Integer, Integer> map;
    private final int CAPACITY;
    public LRUCache(int capacity) {
        CAPACITY = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest){
                return size() > CAPACITY;
            }
        };
    }

    // This method works in O(1)
    public int get(int key){
        return map.getOrDefault(key, -1);
    }

    // This method works in O(1)
    public void put(int key, int value) {
        map.put(key, value);
    }
}

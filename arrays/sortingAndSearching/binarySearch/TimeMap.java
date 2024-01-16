package arrays.sortingAndSearching.binarySearch;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// Most Maintainable Code. For fastest code, just implement your own RB-Tree.
public class TimeMap {

    private final Map<String, TreeMap<Integer, String>> data;

    public TimeMap() {
        // Initialize a hashmap to store key-value pairs.
        data = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        // If the key is not already in the hashmap, create a new TreeMap.
        data.putIfAbsent(key, new TreeMap<>());

        // Add the timestamp and value to the TreeMap associated with the key.
        data.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        // If the key is not in the hashmap, return an empty string.
        if (!data.containsKey(key)) return "";

        // Get the TreeMap associated with the key.
        TreeMap<Integer, String> timestamps = data.get(key);

        // Use TreeMap's floorKey method to find the largest timestamp less than or equal to the given timestamp.
        Integer floorKey = timestamps.floorKey(timestamp);

        // If floorKey is null, no such timestamp exists, return an empty string.
        if (floorKey == null) return "";

        // Return the value associated with the found timestamp.
        return timestamps.get(floorKey);
    }

    public static void test() {
        // Example usage:
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        System.out.println(timeMap.get("foo", 1));  // Output: "bar"
        System.out.println(timeMap.get("foo", 3));  // Output: "bar"
        System.out.println(timeMap.get("foo", 5));  // Output: "bar"
    }
}

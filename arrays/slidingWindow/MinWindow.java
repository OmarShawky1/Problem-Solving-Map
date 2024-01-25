package arrays.slidingWindow;

import java.util.*;

public class MinWindow {

    // Most maintainable
    public String minWindow(String s, String t) {
        // Check for null or empty strings
        if (s == null || t == null || s.length() == 0 || t.length() == 0) return "";

        // Map to store the count of characters in string 't'
        Map<Character, Integer> targetCharCount = new HashMap<>();
        // Populate the map with character counts from string 't'
        for (char c : t.toCharArray()) targetCharCount.put(c, targetCharCount.getOrDefault(c, 0) + 1);

        // Pointers to represent the window
        int left = 0, right = 0;
        // Minimum length of the window
        int minLength = Integer.MAX_VALUE;
        // Start index of the minimum window substring
        int minLeft = 0;
        // Number of characters from 't' that are still needed to form a valid window
        int requiredChars = t.length();

        // Slide the window through the string 's'
        while (right < s.length()) {
            // Current character in the window
            char currentChar = s.charAt(right);

            // Update the count for the current character in the map
            if (targetCharCount.containsKey(currentChar)) {
                targetCharCount.put(currentChar, targetCharCount.get(currentChar) - 1);
                // If the count becomes non-negative, decrement the requiredChars count
                if (targetCharCount.get(currentChar) >= 0) requiredChars--;
            }

            // Check if a valid window is found
            while (requiredChars == 0) {
                // Update the minimum window length and start index
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }

                // Character at the left end of the window
                char leftChar = s.charAt(left);
                // Update the map and requiredChars count for the left character
                if (targetCharCount.containsKey(leftChar)) {
                    targetCharCount.put(leftChar, targetCharCount.get(leftChar) + 1);
                    if (targetCharCount.get(leftChar) > 0) requiredChars++;
                }

                // Move the left pointer to the right
                left++;
            }

            // Move the right pointer to the right
            right++;
        }

        // Return the minimum window substring, or an empty string if not found
        return (minLength == Integer.MAX_VALUE) ? "" : s.substring(minLeft, minLeft + minLength);
    }

    public static void test() {
        MinWindow m = new MinWindow();
        //assert m.minWindow("a", "a").equals("a");
        System.out.println(m.minWindow("ADOBECODEBANC", "ABC"));
        assert m.minWindow("ADOBECODEBANC", "ABC").equals("BANC");
        assert m.minWindow("a", "aa").equals("");
    }
}

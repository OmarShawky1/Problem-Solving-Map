package arrays.slidingWindow;


public class CharacterReplacement {
    /**
     * @param s source string which contains replaceable substring
     * @param k characters that can be replaced in string s
     * @return maximum size for the variable sliding window.
     */

    // Most Maintainable
    public int characterReplacement(String s, int k) {
        int[] count = new int[26]; // To store the count of each character in the current window
        int maxCount = 0; // To keep track of the maximum repeating character count in the current window
        int start = 0; // Start of the current window
        int end; // End of the current window

        for (end = 0; end < s.length(); end++) {
            // Expand window size (end++) as long as we still can replace characters.
            char currentChar = s.charAt(end);
            count[currentChar - 'A']++; // Update the count of the current character in the window
            maxCount = Math.max(maxCount, count[currentChar - 'A']); // Update maxCount

            // If we reached max number of characters we can replace, shift to right (start++ & end++).
            // If the total number of characters in the current window - maxCount exceeds k, shrink the window (start++)
            // that is because k = maxcount + some numbers that can be replaced
            // For some reason, replacing "if" with "while" will make code 50% faster!
            if (end - start + 1 - maxCount > k) {
                char startChar = s.charAt(start);
                count[startChar - 'A']--; // Decrease the count of the character at the start of the window
                start++; // Move the start pointer to the right
            }
        }
        return end - start;
    }

    // Most Maintainable compacted
    public int characterReplacement2(String s, int k) {
        int[] count = new int[26]; // To store the count of each character in the current window
        int maxCount = 0, start = 0, end;
        for (end = 0; end < s.length(); end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            if (end - start + 1 - maxCount > k) count[s.charAt(start++) - 'A']--;
        }
        return end - start;
    }

    public static void test() {
        CharacterReplacement c = new CharacterReplacement();
        assert c.characterReplacement("ABAB", 2) == 4;
        assert c.characterReplacement("AABABBA", 1) == 4;
    }
}

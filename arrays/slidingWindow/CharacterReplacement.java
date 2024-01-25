package arrays.slidingWindow;

public class CharacterReplacement {


    public int characterReplacement(String s, int k) {
        int[] count = new int[26]; // To store the count of each character in the current window
        int maxCount = 0; // To keep track of the maximum repeating character count in the current window
        int start = 0; // Start of the current window
        int maxLength = 0; // Length of the longest substring with repeating characters

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            count[currentChar - 'A']++; // Update the count of the current character in the window
            maxCount = Math.max(maxCount, count[currentChar - 'A']); // Update maxCount

            // If the total number of characters in the current window minus the maxCount exceeds k,
            // we need to shrink the window by moving the start pointer
            // that is because k = maxcount + some numbers that can be replaced
            while (end - start + 1 - maxCount > k) {
                char startChar = s.charAt(start);
                count[startChar - 'A']--; // Decrease the count of the character at the start of the window
                start++; // Move the start pointer to the right
            }

            // Update the maxLength with the length of the current window
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    public static void test() {
        CharacterReplacement c = new CharacterReplacement();
        assert c.characterReplacement("ABAB", 2) == 4;
        assert c.characterReplacement("AABABBA", 1) == 4;
    }
}

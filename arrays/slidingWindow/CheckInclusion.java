package arrays.slidingWindow;

import java.util.Arrays;

public class CheckInclusion {

    // Not optimal
    public boolean checkInclusion1(String s1, String s2) {
        int k = s1.length();
        int size = 0;
        int[] count = new int[26];

        int i = 0;
        while (i < s2.length()) {
            if (size < k) {
                count[s2.charAt(i++) - 'a']++;
                size++;
            }
            if (size == k) {
                int[] tempCount = count.clone();
                for (int j = 0; j < s1.length(); j++) {
                    char c = s1.charAt(j);
                    if (tempCount[c - 'a']-- == 0) {
                        count[s2.charAt(i - k) - 'a']--;
                        size--;
                        break;
                    }
                    if (j == s1.length() - 1) return true;
                }
            }
        }
        return false;
    }

    // Most maintainable
    public boolean checkInclusion2(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1CharCount = new int[26];
        int[] windowCharCount = new int[26];

        // Initialize the count array for s1
        for (char c : s1.toCharArray()) s1CharCount[c - 'a']++;

        // Initialize the count array for the initial window in s2
        for (int i = 0; i < s1.length(); i++) windowCharCount[s2.charAt(i) - 'a']++;

        // Check if the initial window is a permutation of s1
        if (Arrays.equals(s1CharCount, windowCharCount)) return true;

        // Slide the window through the rest of s2
        for (int i = s1.length(); i < s2.length(); i++) {
            // Update the window count for the new character entering the window
            windowCharCount[s2.charAt(i) - 'a']++;

            // Remove the count of the character leaving the window
            windowCharCount[s2.charAt(i - s1.length()) - 'a']--;

            // Check if the current window is a permutation of s1
            if (Arrays.equals(s1CharCount, windowCharCount)) return true;
        }

        return false;
    }

    // Optimal solution
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length(); // Important because O(n) for length() is n
        int k = n1, left = 0;
        int[] count = new int[26];
        char[] s2Arr = s2.toCharArray();

        for (char c : s1.toCharArray()) ++count[c - 'a'];

        for (int right = 0; right < n2; right++) {
            // If char count is 1 or more, decrement remaining window size k
            if (count[s2Arr[right] - 'a']-- > 0) k--;

            // If we reached maximum comparison window size
            while (k == 0) {
                // And all our window size is correct (right is not so far ahead of left more than k), return true
                if (right - left + 1 == n1) return true;

                // else, if value is -1 make it 0 and loop. if it is 0 or more, increase window size and break.
                if (++count[s2Arr[left++] - 'a'] > 0) k++;
            }
        }
        return false;
    }

    public static void test() {
        CheckInclusion c = new CheckInclusion();
        assert c.checkInclusion("ab", "eidbaooo");
        assert !c.checkInclusion("ab", "eidboaoo");
        assert c.checkInclusion("adc", "dcda");
        assert !c.checkInclusion("hello", "ooolleoooleh");
    }
}

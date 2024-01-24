package arrays.slidingWindow;

import java.util.HashSet;

/**
 * Find length of longest substring of unrepeated letters
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">...</a>
 */
public class LengthOfLongestSubstring {
    // Maintainable solution using sliding window or Bag using Hashset
    public int lengthOfLongestSubstring1(String s) {
        HashSet<Character> set = new HashSet<>();
        int maxL = 0, start = 0, end = 0;
        while (end < s.length()) { // While we didn't reach end
            if (!set.add(s.charAt(end))) set.remove(s.charAt(start++)); // if current character is repeated, shift right
            else maxL = Math.max(maxL, (++end) - start); // else, expand right (++end) then compute max length
        }
        return maxL;
    }

    // Fastest solution (100% on LC)
    public int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        int maxL = 0, start = 0, end = 0;
        while (end < s.length()) { // While we didn't reach end
            if (freq[s.charAt(end)] == 1) freq[s.charAt(start++)] = 0; // if current character is repeated, shift right
            else { // else, expand right (++end) then compute max length
                freq[s.charAt(end)] = 1;
                maxL = Math.max(maxL, (++end) - start);
            }
        }
        return maxL;
    }

    public static void test() {
        LengthOfLongestSubstring l = new LengthOfLongestSubstring();
        assert l.lengthOfLongestSubstring("abcabcbb") == 3;
        assert l.lengthOfLongestSubstring("bbbbb") == 1;
        assert l.lengthOfLongestSubstring("pwwkew") == 3;
    }
}

package arrays.slidingWindow;

import java.util.HashSet;

/**
 * Find length of longest substring of unrepeated letters
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">...</a>
 */
public class LengthOfLongestSubstring {

    /**
     * Return len of longest substring [a-z]*
     *
     * @param s
     * @return len
     */

    // Simplest solution
    public int lengthOfLongestSubstring1(String s) {
        HashSet<Character> set = new HashSet<>();
        int maxL = 0, start = 0, end = 0;
        while (end < s.length()) { // While we didn't reach end
            if (!set.add(s.charAt(end))) set.remove(s.charAt(start++)); // if current character is repeated, shift left
            else maxL = Math.max(maxL, (++end) - start); // else, expand right (++end) then compute max length
        }
        return maxL;
    }

    // Fastest solution
    public int lengthOfLongestSubstring(String s) {
        int[] ints = new int[256];
        int maxL = 0, start = 0, end = 0;
        while (end < s.length()) { // While we didn't reach end
            if (ints[s.charAt(end)] == 1) ints[s.charAt(start++)] = 0; // if current character is repeated, shift left
            else { // else, expand right (++end) then compute max length
                ints[s.charAt(end++)] = 1;
                maxL = Math.max(maxL, (end) - start);
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

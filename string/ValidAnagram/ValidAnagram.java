package string.ValidAnagram;

import java.util.HashMap;

/**
 * Check if two strings have same letters despite the order.
 * Problem Link: <a href="https://leetcode.com/problems/valid-anagram/"> Valid Anagram </a>
 */
public class ValidAnagram {

    /**
     * Return true if t is anagram of f; false otherwise.
     *
     * @param s
     * @param t
     * @return bool
     */
    public boolean isAnagram1(String s, String t) {

        // Check length equality
        if (s.length() != t.length()) return false;

        // Store s string in HashMap
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == null) map.put(c, 1);
            else map.put(c, map.get(c) + 1); // add 1 to value when retrieved
            // map.merge(c, 1, Integer::sum);
        }

        // Search for t letters in s
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (map.get(c) == null || map.get(c) == 0) return false;
            else map.merge(c, -1, Integer::sum);
        }
        return true;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) if (--alphabet[t.charAt(i) - 'a'] < 0) return false;
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }

    public static void test() {
        ValidAnagram v = new ValidAnagram();
        assert v.isAnagram("anagram", "nagaram");
        assert !v.isAnagram("rat", "car");
        assert !v.isAnagram("aacc", "ccac");
    }

}

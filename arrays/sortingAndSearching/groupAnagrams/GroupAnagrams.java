package arrays.sortingAndSearching.groupAnagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Group all anagrams. Take list of strings and return a list of lists where each list is a list of anagrams.
 * Problem Link: <a href="https://leetcode.com/problems/group-anagrams/">...</a>
 */
public class GroupAnagrams {

    // Brute Force. Runs but TLC.
    public List<List<String>> groupAnagrams1(String[] strs) {
        ArrayList<List<String>> listOfLists = new ArrayList<>();

        for (String s : strs) {
            // Initialize listOfLists if it is empty
            if (listOfLists.isEmpty()) {
                listOfLists.add(new ArrayList<>());
                listOfLists.get(0).add(s);
                continue;
            }

            char[] sChar = s.toCharArray();
            Arrays.sort(sChar);
            boolean added = false;

            // Otherwise, for each List of strings, compare first item in it with current string and add if it is anagram
            for (List<String> ls : listOfLists) {
                char[] lsChar = ls.get(0).toCharArray();
                Arrays.sort(lsChar);
                if (Arrays.equals(lsChar, sChar)) {
                    ls.add(s);
                    added = true;
                    break;
                }
            }
            if (!added){
                listOfLists.add((new ArrayList<>()));
                listOfLists.get(listOfLists.size() - 1).add(s);
            }
        }

        return listOfLists;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> los = new HashMap<>();
        for (String s: strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sortedS = new String(chars);

            if (!los.containsKey(sortedS)) los.put(sortedS, new ArrayList<>());
            los.get(sortedS).add(s);
        }

        return new ArrayList<>(los.values());
    }


    public static void test() {
        GroupAnagrams g = new GroupAnagrams();
        assert g.groupAnagrams(new String[]{""}).get(0).get(0).isEmpty();
        assert g.groupAnagrams(new String[]{"a"}).get(0).get(0).equals("a");
        assert g.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).get(0).get(0).
                equals("eat");
        assert g.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).get(0).get(1).
                equals("tea");
        assert g.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).get(0).get(2).
                equals("ate");
        assert g.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).get(1).get(0).
                equals("tan"); // won't work except with brute force
        assert g.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).get(1).get(1).
                equals("nat");
        assert g.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).get(2).get(0).
                equals("bat");
    }
}

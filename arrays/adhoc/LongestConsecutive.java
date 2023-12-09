package arrays.adhoc;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {

    // Original Solution link: https://leetcode.com/problems/longest-consecutive-sequence/solutions/41130/another-accepted-java-o-n-solution/
    public int longestConsecutive1(int[] nums) {
        int max = 0;

        Set<Integer> set = new HashSet<>();
        for (int j : nums) set.add(j);

        for (int j : nums) {
            int count = 1;

            // look left
            int num = j;
            while (set.remove(--num)) count++;

            // look right
            num = j;
            while (set.remove(++num)) count++;
            max = Math.max(max, count);
        }
        return max;
    }

    public static void test() {
        LongestConsecutive l = new LongestConsecutive();
    }
}

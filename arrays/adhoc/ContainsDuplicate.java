package arrays.adhoc;

import java.util.HashSet;

/**
 * Return true if array contains duplicate.
 * Problem Link: <a href="https://leetcode.com/problems/contains-duplicate/submissions/">...</a>
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) if (!set.add(num)) return true;
        return false;
    }

    public static void test() {
        ContainsDuplicate c = new ContainsDuplicate();
        assert c.containsDuplicate(new int[]{1, 2, 3, 1});
        assert !c.containsDuplicate(new int[]{1, 2, 3, 4});
    }
}

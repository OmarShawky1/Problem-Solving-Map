package arrays.sortingAndSearching;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Retrieve the compliment c of number n in array arr where n+c= target
 * Problem Link: <a href="https://leetcode.com/problems/two-sum">...</a>
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {

        // Brute Force
        for (int i = 0; i < nums.length; i++) {
            int numsI = nums[i];
            for (int j = i + 1; j < nums.length; j++) if (numsI + nums[j] == target) return new int[]{i, j};
        }
        return new int[]{-1, -1};
    }

    // Using Hash table. 100% on LC
    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) return new int[]{map.get(target - nums[i]), i};
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void test() {
        TwoSum t = new TwoSum();
        assert Arrays.equals(t.twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{0, 1});
        assert Arrays.equals(t.twoSum(new int[]{3, 2, 4}, 6), new int[]{1, 2});
        assert Arrays.equals(t.twoSum(new int[]{3, 3}, 6), new int[]{0, 1});
    }
}

package arrays;

/**
 * Finds maximum difference between elements i and any item after it.
 * Problem Link: <a href="https://leetcode.com/problems/maximum-difference-between-increasing-elements/submissions/1047334223/">2016. Maximum Difference Between Increasing Elements</a>
 */
public class MaximumDifference {
    /**
     * Multiple functions/solutions for maximum Difference Problem.
     *
     * @param nums int[]
     * @return maxDifference, -1 if not found
     */
    // Brute Force Solution
    public int maximumDifference1(int[] nums) {
        int max = -Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = nums[j] - nums[i];
                if (0 < diff && diff > max) max = diff;
            }
        }
        return max == -Integer.MAX_VALUE ? -1 : max;
    }

    // Brute Force: Enhanced: Start from smaller values only
    public int maximumDifference2(int[] nums) {
        int max = -Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = nums[j] - nums[i];
                if (diff <= 0 && j - i == 1) break;
                if (diff > max) max = diff;
            }
        }
        return max == -Integer.MAX_VALUE ? -1 : max;
    }

    // Two-Pointer: O(n) and space O(1)
    public int maximumDifference(int[] nums) {
        int max = -1;
        for (int i = 1, min = nums[0]; i < nums.length; i++) {
            if (nums[i] > min) max = Math.max(nums[i] - min, max);
            else min = nums[i];
        }
        return max;
    }

    public static void test() {
        MaximumDifference s = new MaximumDifference();
        assert (s.maximumDifference(new int[]{7, 1, 5, 4}) == 4); // 4
        assert (s.maximumDifference(new int[]{9, 4, 3, 2}) == -1); // -1
        assert (s.maximumDifference(new int[]{1, 5, 2, 10}) == 9); // 9
        assert (s.maximumDifference(new int[]{2, 7, 5, 3, 5, 4, 9, 10, 1}) == 8); // 8
        assert (s.maximumDifference(new int[]{7, 1, 5, 4, 9, 0, 5, 10}) == 10) :
                s.maximumDifference(new int[]{7, 1, 5, 4, 9, 0, 5, 10}); // 10
    }
}
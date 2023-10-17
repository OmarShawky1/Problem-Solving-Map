package arrays.slidingWindow;

/**
 * Find max circular subarray sum
 * Problem Link:
 */
public class MaxSubarraySumCircular {

    // Kaden's Algorithm, TLE
    public int maxSubarraySumCircular1(int[] nums) {
        int max = Integer.MIN_VALUE;
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            int maxCurr = 0;
            for (int j = 0; j < len; j++) {
                maxCurr += nums[(i + j) % len];
                max = Math.max(max, maxCurr);
                maxCurr = Math.max(maxCurr, 0); // reset maxCurr if it found -ve value. Deals with -ve arrays
            }
        }
        return max;
    }
    // Solution original inspiration: https://leetcode.com/problems/maximum-sum-circular-subarray/solutions/3066058/c-easy-solution-with-explaination-in-o-n-time-complexity/
    public int maxSubarraySumCircular(int[] nums) {
        int nonCircularSum = kadaneMaxSum(nums);

        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
            nums[i] = -nums[i];
        }
        int circularSum = totalSum + kadaneMaxSum(nums);
        if (circularSum == 0) return nonCircularSum;

        return Math.max(circularSum, nonCircularSum);
    }

    // Check MaximumSubarray.java
    private int kadaneMaxSum(int[] nums) {
        int max = Integer.MIN_VALUE, maxCur = 0;
        for (int num : nums) max = Math.max((maxCur = Math.max(num, maxCur + num)), max);
        return max;
    }

    public static void test() {
        MaxSubarraySumCircular m = new MaxSubarraySumCircular();

        assert m.maxSubarraySumCircular(new int[]{1, -2, 3, -2}) == 3;
        assert m.maxSubarraySumCircular(new int[]{5, -3, 5}) == 10;
        assert m.maxSubarraySumCircular(new int[]{-3, -2, -3}) == -2;
    }
}

package arrays.slidingWindow;

public class MaximumAbsoluteSum {
    public int maxAbsoluteSumK(int[] nums) {
        return Math.max(maxSubarray(nums), Math.abs(minSubarray(nums)));
    }

    private int minSubarray(int[] nums) {
        int min = Integer.MAX_VALUE, minCurr = 0;
        for (int num : nums) min = Math.min((minCurr = Math.min(num, minCurr + num)), min); //check MaximumSubarray.java
        return min;
    }

    private int maxSubarray(int[] nums) {
        int max = Integer.MIN_VALUE, maxCurr = 0;
        for (int num : nums) max = Math.max((maxCurr = Math.max(num, maxCurr + num)), max); //check MaximumSubarray.java
        return max;
    }

    public int maxAbsoluteSum(int[] nums) {
        int s = 0, min = 0, max = 0;
        for (int num : nums) {
            s += num;
            min = Math.min(min, s);
            max = Math.max(max, s);
        }
        return max - min;
    }

    public static void test() {
        MaximumAbsoluteSum m = new MaximumAbsoluteSum();
        assert m.maxAbsoluteSum(new int[]{1, -3, 2, 3, -4}) == 5;
        assert m.maxAbsoluteSum(new int[]{2, -5, 1, -4, 3, -2}) == 8;
    }
}

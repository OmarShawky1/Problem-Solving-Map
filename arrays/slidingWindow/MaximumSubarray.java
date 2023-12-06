package arrays.slidingWindow;

public class MaximumSubarray {
    // Brute: TLE
    public int maxSubArray1(int[] nums) {
        int max = nums[0];

        for (int i = 0; i < nums.length; i++) {
            for (int k = 1; k <= nums.length; k++) {
                int tempM = 0;
                for (int j = i; j < k; j++) {
                    tempM += nums[j];
                    max = Math.max(max, tempM);
                }
            }
        }
        return max;
    }

    // Brute Enhanced: TLE
    public int maxSubArray2(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int[] pre = new int[nums.length - i];
            pre[0] = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int temp = pre[j - i - 1] + nums[j];
                pre[j - i] = temp;
                max = Math.max(temp, max);
            }
            max = Math.max(pre[0], max);
        }
        return max;
    }

    // Prefix Sum 1
    public int maxSubArrayP1(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int[] pre = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            int temp = pre[i] + nums[i]; // Create prefix sum
            pre[i + 1] = temp;
            max = Math.max(temp, max); // Get max between current max and old max.
            if (i != 0) max = Math.max(max, temp - min); // get max between max and current max - min prefix (for -ve n)
            min = Math.min(temp, min); // calculate min after max (constrain)
        }
        return max;
    }

    // Prefix Sum 2 (enhanced). 100% on LC
    public int maxSubArrayP2(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int pre = 0;

        for (int i = 0; i < nums.length; i++) {
            pre += nums[i]; // Create prefix sum
            max = Math.max(pre, max); // Get max between current max and old max.
            if (i != 0) max = Math.max(max, pre - min); // get max between max and current max - min prefix (for -ve n)
            min = Math.min(pre, min); // calculate min after max (constrain)
        }
        return max;
    }

    // Variable-size sliding window (Kaden's Algorithm). 100% on LC
    public int maxSubArrayV1(int[] nums) {
        int maxCur = 0;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            maxCur += num; // Create prefix sum
            max = Math.max(maxCur, max); // Get max between current max and old max.
            if (maxCur < 0) maxCur = 0; // reset current max if it is < 0
        }
        return max;
    }

    // Variable-size sliding window (Kaden's Algorithm Enhanced). 100% on LC
    public int maxSubArrayV2(int[] nums) {
        int maxCur = 0;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            maxCur = Math.max(num, maxCur + num); // Create prefix sum, reset current max if it is < 0 (num > old max)
            max = Math.max(maxCur, max); // Get max between current max and old max.
        }
        return max;
    }

    // Variable-size sliding window (Kaden's Algorithm Enhanced). 100% on LC
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, maxCur = 0;
        for (int num : nums) max = Math.max((maxCur = Math.max(num, maxCur + num)), max); // create prefix sum, reset if -ve to smallest -ve value, choose max between current max and old max
        return max;
    }

    public static void test() {
        MaximumSubarray m = new MaximumSubarray();
        assert m.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6;
        assert m.maxSubArray(new int[]{1}) == 1;
        assert m.maxSubArray(new int[]{5, 4, -1, 7, 8}) == 23;
        assert m.maxSubArray(new int[]{-2, -1}) == -1;
        assert m.maxSubArray(new int[]{-2, 1}) == 1;
        assert m.maxSubArray(new int[]{-2, -3, -1}) == -1;
        assert m.maxSubArray(new int[]{-1, -2}) == -1;
        assert m.maxSubArray(new int[]{1, -1, -2}) == 1;
        assert m.maxSubArray(new int[]{0, -3, 1, 1}) == 2;
    }
}

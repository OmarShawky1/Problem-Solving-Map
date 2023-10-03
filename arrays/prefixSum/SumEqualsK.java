package arrays.prefixSum;

import java.util.HashMap;

/**
 * Find subarrays of nums that give sum k
 * Problem link: https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SumEqualsK {
    /**
     * return number of subarrays that has sum of k
     *
     * @param nums
     * @param k
     * @return sum
     */

    // Brute Force, infeasible due to constrains
    public int subarraySum1(int[] nums, int k) {
        int sum = 0;
        //For each window size, start with window = len - 1
        for (int w = nums.length; w > 0; w--) {
            // for each window index i
            for (int i = 0; (i < nums.length) && (i + w - 1 < nums.length); i++) {
                int tempWS = 0; // Temp window sum
                for (int j = i; j < i + w; j++) tempWS += nums[j];
                if (tempWS == k) sum++;
            }
        }
        return sum;
    }

    // Enhanced brute force
    public int subarraySum2(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int tempWS = 0;
            for (int j = i; j < nums.length; j++) {
                tempWS += nums[j];
                if (tempWS == k) sum++;
            }
        }
        return sum;
    }

    // Solution Link: <a href="https://leetcode.com/problems/subarray-sum-equals-k/solutions/803317/java-solution-with-detailed-explanation/">...</a>
    public int subarraySum3(int[] nums, int k) {
        if (nums.length == 0) return 0;
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // Create prefix sum array

            // Cumulative addition; Check if same elem resulted in multiple "k"s
            if (map.containsKey(sum - k)) count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // Modified version of the one below
    public int subarraySum4(int[] nums, int k) {
        if (nums.length == 0) return 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        // Create prefix sum array
        int[] prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) prefix[i + 1] = prefix[i] + nums[i];

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(prefix[i + 1] - k)) count += map.get(prefix[i + 1] - k);
            map.put(prefix[i + 1], map.getOrDefault(prefix[i + 1], 0) + 1);
        }
        return count;
    }

    // Fastest with 99.89%. Code explanation is in code 3
    // Instead of using 1 base index array, we use 0 base and deal with the specific case using 2 for loops
    public int subarraySum(int[] nums, int k) {
        if (nums.length == 0) return 0;
        int count = 0;

        // Create prefix sum array
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) prefix[i] = prefix[i - 1] + nums[i];
        for (int i = 0; i < nums.length; i++) if (prefix[i] == k) count++;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(prefix[i] - k)) count += map.get(prefix[i] - k);
            map.put(prefix[i], map.getOrDefault(prefix[i], 0) + 1);
        }
        return count;
    }

    public static void test() {
        SumEqualsK s = new SumEqualsK();
        assert s.subarraySum(new int[]{1, 1, 1}, 2) == 2;
        assert s.subarraySum(new int[]{1, 2, 3}, 3) == 2;
        assert s.subarraySum(new int[]{1, -1, 0}, 0) == 3;
        assert s.subarraySum(new int[]{1, -1, 0, 4, 5, -5}, 0) == 4;
        assert s.subarraySum(new int[]{1}, 1) == 1;
    }
}

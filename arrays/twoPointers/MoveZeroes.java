package arrays.twoPointers;

import java.util.Arrays;

/**
 * Problem Link: https://leetcode.com/problems/move-zeroes
 */
public class MoveZeroes {
    // private int[] nums; // For Testing

    // BF, Accepted
    public void moveZeroes1(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (nums[end] == 0 && end > 0) end--; // Make end stand at last non-zero number

        while (start < end) {
            if (nums[start] == 0) { // Swap
                for (int i = start + 1; i < end + 1; i++)
                    nums[i - 1] = nums[i];
                nums[end--] = 0;
            } else start++;
        }
        // this.nums = nums; For Testing
    }

    // Accepted, 68%
    // Solution Source: https://leetcode.com/problems/move-zeroes/solutions/172432/the-easiest-but-unusual-snowball-java-solution-beats-100-o-n-clear-explanation/
    public void moveZeroes2(int[] nums) {
        int zStart = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (zStart == -1) zStart = i; // initialize sliding window
            } else { // swap first item in sliding with start (index i)
                if (zStart != -1) {
                    nums[zStart++] = nums[i];
                    nums[i] = 0;
                }
            }
        }
    // this.nums = nums; For Testing
    }

    // Optimal, 100% on LC
    // Solution Source: https://leetcode.com/problems/move-zeroes/solutions/3549476/easy-java-solution-beats-99-9/
    public void moveZeroes(int[] nums) {
        int zeroStart = 0;
        for (int num : nums) if (num != 0) nums[zeroStart++] = num;
        while (zeroStart < nums.length) nums[zeroStart++] = 0;
    }

    public static void test() {
        MoveZeroes m = new MoveZeroes();
        m.moveZeroes(new int[]{0, 1, 0, 3, 12});
        // assert Arrays.equals(m.nums, new int[]{1, 3, 12, 0, 0});

        m.moveZeroes(new int[]{0});
        // assert Arrays.equals(m.nums, new int[]{0});
    }
}

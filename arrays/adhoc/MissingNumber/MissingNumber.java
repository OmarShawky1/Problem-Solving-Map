package arrays.adhoc.MissingNumber;

import java.util.Arrays;

/**
 * Find missing number from the unordered numbers from 0 to n
 * Problem Link: <a href="https://leetcode.com/problems/missing-number/">...</a>
 */
public class MissingNumber {

    // Dual Pivot Array sort

    /**
     * Return number missing between 0 to n
     *
     * @param nums
     * @return num
     */
    public int missingNumber1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) if (i != nums[i]) return i;
        return nums.length; // return length because it does not obey the comparison above
    }

    // Xor, Idea is very simple, if you xor number with itself it will be 0 and the missing number will appear.
    // Solution source: https://leetcode.com/problems/missing-number/solutions/69791/4-line-simple-java-bit-manipulate-solution-with-explaination/
    public int missingNumber(int[] nums) {
        int xor = 0, i;
        for (i = 0; i < nums.length; i++) xor ^= i ^ nums[i];
        return xor ^ i;
    }

    public static void test() {
        MissingNumber m = new MissingNumber();

        assert m.missingNumber(new int[]{3, 0, 1}) == 2;
        assert m.missingNumber(new int[]{0, 1}) == 2;
        assert m.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}) == 8;
    }
}

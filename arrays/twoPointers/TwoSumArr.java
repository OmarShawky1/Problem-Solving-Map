package arrays.twoPointers;

import java.util.Arrays;

public class TwoSumArr {
    int[] numbers;

    // Simplest Implementation
    public int[] twoSum1(int[] numbers, int target) {
        int complimentary; // less than the least number in constrains

        for (int i = 0; i < numbers.length; i++) {
            complimentary = target - numbers[i];
            int temp = Arrays.binarySearch(numbers, i + 1, numbers.length, complimentary);
            if (temp > -1) return new int[]{i + 1, temp + 1};
        }
        return new int[]{-1, -1}; // impossible to reach this line
    }

    // Using my own Binary Search Imp. Same Complexity.
    public int[] twoSum2(int[] numbers, int target) {
        int complimentary; // less than the least number in constrains
        this.numbers = numbers;

        for (int i = 0; i < numbers.length; i++) {
            complimentary = target - numbers[i];
            int temp = binarySearch(i + 1, numbers.length, complimentary);
            if (temp > -1) return new int[]{i + 1, temp + 1};
        }
        return new int[]{-1, -1}; // impossible to reach this line
    }

    // Using my own search
    private int binarySearch(int start, int end, int target) {
        int mid;
        while (start < end) {
            mid = (start + end) / 2;
            if (numbers[mid] == target) return mid;
            else if (numbers[mid] > target) end = mid;
            else start = mid + 1;
        }
        return -1; // not found
    }

    // Fastest Solution O(n).
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int tmp;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) break;
            tmp = target - numbers[right];
            while (numbers[left] < tmp) left++;
            if (numbers[left] + numbers[right] == target) break;
            tmp = target - numbers[left];
            while (numbers[right] > tmp) right--;
        }
        return new int[]{left + 1, right + 1};
    }


    public static void test() {
        TwoSumArr t = new TwoSumArr();
        assert Arrays.equals(t.twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{1, 2});
        assert Arrays.equals(t.twoSum(new int[]{2, 3, 4}, 6), new int[]{1, 3});
        assert Arrays.equals(t.twoSum(new int[]{3, 3}, 6), new int[]{1, 2});
        assert Arrays.equals(t.twoSum(new int[]{5, 25, 75}, 100), new int[]{2, 3});
    }
}

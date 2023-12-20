package arrays.sortingAndSearching.binarySearch;

public class SearchRotatedArray {
    // Optimal solution
    // Original Solution from https://leetcode.com/problems/search-in-rotated-sorted-array/solutions/3879263/100-binary-search-easy-video-o-log-n-optimal-solution/
    // Modified from original solution. right = mid -1 instead of right = mid
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            // Is left is sorted?
            // if (left == mid || nums[left] < nums[mid]) // Same as below condition but more clearer
            if (nums[left] <= nums[mid]) { // the = in the condition is to check if left and mid pointer are the same
                // Is target between left and mid?
                if (nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            } else { // Else
                // is target between mid and right
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }

    public static void test() {
        SearchRotatedArray s = new SearchRotatedArray();
        assert s.search(new int[]{3, 4, 5, 6, 7, 8, 9, 10, 0, 1, 2}, 9) == 6;
        assert s.search(new int[]{3, 1}, 1) == 1;
    }
}
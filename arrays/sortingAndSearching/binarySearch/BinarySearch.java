package arrays.sortingAndSearching.binarySearch;

public class BinarySearch {

    // Princeton Implementation
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (target < nums[mid]) right = mid - 1;
            else if (target > nums[mid]) left = mid + 1;
            else return mid;
        }
        return -1;
    }

    public int search1(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;

        while (left < right) {
            mid = (right + left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) right = mid;
            else left = mid + 1;
        }
        return -1;
    }

    public static void test() {
        BinarySearch b = new BinarySearch();
        assert b.search(new int[]{-1, 0, 3, 5, 9, 12}, 9) == 4;
        assert b.search(new int[]{-1, 0, 3, 5, 9, 12}, 2) == -1;
        assert b.search(new int[]{1, 3}, 1) == 0;
    }
}

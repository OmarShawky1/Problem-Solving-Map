package arrays.sortingAndSearching.binarySearch;

public class FindMin {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // If the array is not rotated or has only one element
        if (nums[left] <= nums[right]) return nums[left];

        while (left <= right) {
            // If there's only one element left in the search
            if (left == right) return nums[left];

            int mid = left + (right - left) / 2;

            // Check if the mid element is the minimum
            if (nums[mid] > nums[mid + 1]) return nums[mid + 1];
            if (nums[mid - 1] > nums[mid]) return nums[mid];

            // If the mid element is greater than the leftmost element,
            // search in the right half
            if (nums[mid] > nums[left]) left = mid + 1;
            else right = mid - 1; // Else, search in the left half
        }
        return -1; // This line won't be reached if the array is valid
    }

    public static void test() {
        FindMin f = new FindMin();
        assert f.findMin(new int[]{3,4,5,1,2}) == 1;
    }
}

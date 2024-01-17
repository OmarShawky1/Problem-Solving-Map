package arrays.sortingAndSearching.binarySearch;

public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // nums1 is X and nums2 is Y

        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        // Pointers for nums1
        int start = 0, end = nums1.length;

        while (start <= end) {
            int partitionX = (start + end) / 2; // Middle, no need for overflow way of writing it.
            int partitionY = (nums1.length + nums2.length + 1) / 2 - partitionX; // 1 is for odd length case.

            // If partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            // If partitionX is length of input then there is nothing on right side. Use +INF for minRightX
            int maxLeftX = partitionX > 0 ? nums1[partitionX - 1] : Integer.MIN_VALUE;
            int minRightX = partitionX < nums1.length ? nums1[partitionX] : Integer.MAX_VALUE;
            int maxLeftY = partitionY > 0 ? nums2[partitionY - 1] : Integer.MIN_VALUE;
            int minRightY = partitionY < nums2.length ? nums2[partitionY] : Integer.MAX_VALUE;

            // Base case, if partitions is found, return
            if (maxLeftX <= minRightY && maxLeftY <= minRightX){
                // If length is odd, take max of maxes
                if ((nums1.length + nums2.length) % 2 != 0) return Math.max(maxLeftX, maxLeftY);
                // else, take the average of (max of maxes and min of mins
                else return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
            }

            // If left value of X is more than right value of Y, We need to move right
            if (maxLeftX > minRightY) end = partitionX - 1;
            else start = partitionX + 1;
        }

        throw new IllegalArgumentException("Input arrays are not sorted");
    }

    public static void test() {
        FindMedianSortedArrays f = new FindMedianSortedArrays();

        assert f.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) == 2.0;
        assert f.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}) == 2.5;
        assert f.findMedianSortedArrays(new int[]{2}, new int[]{}) == 2;
    }
}

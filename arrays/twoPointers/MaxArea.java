package arrays.twoPointers;

public class MaxArea {

    // Most Maintainable
    public int maxArea(int[] height) {
        int max = 0, curMax, left = 0, right = height.length - 1;
        while (left < right) {
            curMax = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, curMax);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return max;
    }

    // Twice as fast
    public int maxArea2(int[] height) {
        int maxArea = 0, start = 0, end = height.length - 1;
        while (start < end) {
            int minHeight = Math.min(height[start], height[end]);
            maxArea = Math.max(maxArea, (minHeight * (end - start)));

            while (start < end && height[start] <= minHeight) start++;
            while (start < end && height[end] <= minHeight) end--;
        }
        return maxArea;
    }


    public static void test() {
        MaxArea m = new MaxArea();
        assert m.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}) == 49;
    }
}

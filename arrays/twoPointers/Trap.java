package arrays.twoPointers;

/**
 * Find the dips (water trapped) between elevations (heights)
 * Problem Link: <a href="https://leetcode.com/problems/trapping-rain-water/">...</a>
 */
public class Trap {
    /**
     * Solution Logic: Find the highest elevation, move from right to it and from left to it and add any dips that are less than current max height
     * @param height
     * @return
     */
    // max height, similar to Two pointers
    public int trap1(int[] height) {
        if (height == null || height.length <= 2) return 0;

        int maxht = 0, index = 0;
        for (int i = 0; i < height.length; i++) if (height[i] > maxht) maxht = height[index = i];

        int localmax = height[0];
        int water = 0;
        for (int i = 0; i < index; i++) {
            if (height[i] < localmax) water += localmax - height[i];
            localmax = Math.max(localmax, height[i]);
        }
        localmax = height[height.length - 1];
        for (int i = height.length - 1; i > index; i--) {
            if (height[i] < localmax) water += localmax - height[i];
            localmax = Math.max(localmax, height[i]);
        }
        return water;
    }

    /**
     * Solution Logic: calculate max right & left heights using prefix and suffix and add dips that are less than minimum of both.
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (height == null || height.length <= 2) return 0;

        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) leftMax[i] = Math.max(leftMax[i - 1], height[i]);

        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) rightMax[i] = Math.max(rightMax[i + 1], height[i]);

        int ans = 0;
        for (int i = 0; i < height.length; i++) ans += Math.min(leftMax[i], rightMax[i]) - height[i];

        return ans;
    }

    /**
     * Solution Logic: Dynamically find the most left and right heights, move the least of both towards the middle until the left & right pointers are equal. throughout the movement, dynamically detect & add dips or increase max height.
     * @param height
     * @return
     */
    // Two pointers
    // 100% on LC
    public int trap(int[] height) {
        if (height == null || height.length <= 2) return 0;

        int totalWater = 0;
        int left = 0;
        int right = height.length - 1;
        int maxLeft = 0;
        int maxRight = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > maxLeft) maxLeft = height[left];
                else totalWater += maxLeft - height[left];
                left++;
            } else {
                if (height[right] > maxRight) maxRight = height[right];
                else totalWater += maxRight - height[right];
                right--;
            }
        }
        return totalWater;
    }

    public static void test() {
        Trap t = new Trap();
        assert t.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}) == 6;
        assert t.trap(new int[]{4, 2, 0, 3, 2, 5}) == 9;
    }
}

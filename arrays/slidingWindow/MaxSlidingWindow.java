package arrays.slidingWindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

public class MaxSlidingWindow {
    // TLE
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < k; i++) pq.add(-nums[i]);
        ans[0] = -pq.peek();

        for (int i = k; i < nums.length; i++) {
            pq.remove(-nums[i - k]);
            pq.add(-nums[i]);
            ans[i - k + 1] = -pq.peek();
        }

        return ans;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];
        int resultIndex = 0;

        // Deque to store indices of elements in the current window
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Remove indices of elements that are out of the current window
            // peek removes from left
            if (!deque.isEmpty() && deque.peek() < i - k + 1)
                deque.poll();

            // Remove indices of elements smaller than the current element (nums(i))
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                deque.pollLast();

            // Add the current index to the deque
            deque.offer(i);

            // Add the maximum element in the current window to the result array
            if (i >= k - 1)
                result[resultIndex++] = nums[deque.peek()];
        }

        return result;
    }

    public static void test() {
        MaxSlidingWindow m = new MaxSlidingWindow();
        assert Arrays.equals(m.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3), new int[]{3, 3, 5, 5, 6, 7});
    }
}

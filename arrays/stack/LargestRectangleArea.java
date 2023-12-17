package arrays.stack;

import java.util.Stack;

public class LargestRectangleArea {

    // Most maintainable Code
    public int largestRectangleArea1(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int index = 0;

        while (index < heights.length) {
            if (stack.isEmpty() || heights[index] >= heights[stack.peek()]) {
                stack.push(index);
                index++;
            } else {
                int topOfStack = stack.pop();
                int area = heights[topOfStack] * (stack.isEmpty() ? index : index - stack.peek() - 1);
                maxArea = Math.max(maxArea, area);
            }
        }

        while (!stack.isEmpty()) {
            int topOfStack = stack.pop();
            int area = heights[topOfStack] * (stack.isEmpty() ? index : index - stack.peek() - 1);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // Fastest Codec
    public int largestRectangleArea(int[] h) {
        if (h.length == 0) return 0;

        int[] st = new int[h.length + 1];
        int top = 0;
        st[top] = -1;
        int s = 0, e = h.length, max = 0;
        while (s < e) {
            while (st[top] != -1 && h[st[top]] >= h[s]) max = Math.max(h[st[top--]] * (s - st[top] - 1), max);
            st[++top] = s++;
        }
        while (st[top] != -1) max = Math.max(h[st[top--]] * (s - st[top] - 1), max);
        return max;
    }

    public static void test() {
        LargestRectangleArea l = new LargestRectangleArea();
        assert l.largestRectangleArea(new int[]{2, 4}) == 4;
        assert l.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}) == 10;
    }
}

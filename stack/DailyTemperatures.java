package stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    // Brute Force, TLE
    public int[] dailyTemperatures1(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int j;
            for (j = i + 1; j < temperatures.length; j++) {
                if (temperatures[i] < temperatures[j]) {
                    ans[i] = j - i;
                    break;
                }
            }
        }
        return ans;
    }

    // Stack Solution
    public int[] dailyTemperatures2(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }

        return result;
    }

    // DP solution using Accumulation Array solution
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int hottest = 0;
        int[] answer = new int[n];

        for (int currDay = n - 1; currDay >= 0; currDay--) {
            int currentTemp = temperatures[currDay];
            if (currentTemp >= hottest) {
                hottest = currentTemp;
                continue;
            }

            int days = 1;
            while (temperatures[currDay + days] <= currentTemp) {
                // day++; // but instead of brute force, why not use past answers to "jump" to answer right away?
                // Use information from answer to search for the next warmer day
                days += answer[currDay + days];
            }
            answer[currDay] = days;
        }
        return answer;
    }


    public static void test() {
        DailyTemperatures d = new DailyTemperatures();
        assert Arrays.equals(d.dailyTemperatures(new int[]{30, 60, 90}), new int[]{1, 1, 0});
        assert Arrays.equals(d.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}),
                new int[]{1, 1, 4, 2, 1, 1, 0, 0});
        assert Arrays.equals(d.dailyTemperatures(new int[]{30,40,50,60}), new int[]{1,1,1,0});

    }
}

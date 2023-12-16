package arrays.stack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class CarFleet {

    // Using 2D Arrays only
    public int carFleet1(int target, int[] pos, int[] speed) {
        int N = pos.length, res = 0;
        double[][] cars = new double[N][2];

        for (int i = 0; i < N; ++i) cars[i] = new double[] {pos[i], (double)(target - pos[i]) / speed[i]};
        Arrays.sort(cars, Comparator.comparingDouble(a -> a[0]));
        double maxTime = 0;
        for (int i = N - 1; i >= 0 ; --i) {
            if (cars[i][1] > maxTime) {
                maxTime = cars[i][1];
                res++;
            }
        }
        return res;
    }

    // Stack solution, Option but neither the fastest or most maintainable
    public int carFleet2(int target, int[] position, int[] speed) {
        if (position.length == 1) return 1;
        Stack<Double> stack = new Stack<>();
        int[][] combine = new int[position.length][2];
        for (int i = 0; i < position.length; i++) {
            combine[i][0] = position[i];
            combine[i][1] = speed[i];
        }

        Arrays.sort(combine, java.util.Comparator.comparingInt(o -> o[0]));
        for (int i = combine.length - 1; i >= 0; i--) {
            double currentTime = (double) (target - combine[i][0]) /
                    combine[i][1];
            if (!stack.isEmpty() && currentTime <= stack.peek()) {
                continue;
            } else {
                stack.push(currentTime);
            }
        }
        return stack.size();
    }

    // Fastest and most maintainable
    public int carFleet(int target, int[] position, int[] speed) {
        float[] time = new float[target];
        for (int i = 0; i < position.length; i++) time[position[i]] = (float) (target - position[i]) / speed[i];

        int fleet = 0;
        float maxTime = 0;
        for (int i = target - 1; i >= 0; i--) {
            if (time[i] > maxTime) {
                maxTime = time[i];
                fleet++;
            }
        }
        return fleet;
    }

    public static void test() {
        CarFleet c = new CarFleet();
        assert c.carFleet(100, new int[]{0, 2, 4}, new int[]{4, 2, 1}) == 1;
    }
}

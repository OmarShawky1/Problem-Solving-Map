package arrays.sortingAndSearching.binarySearch;

public class MinEatingSpeed {

    // Most maintainable code
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0;
        for (int b : piles) maxPile = Math.max(maxPile, b); // return max

        int left = 1, right = maxPile;

        while (left < right) {
            // doing in that way, to handle overflow instead of left + right / 2
            int mid = left + (right - left) / 2;
            if (possible(piles, h, mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private boolean possible(int[] piles, int h, int speed) {
        int hours = 0;
        for (int pile : piles) hours += (pile - 1) / speed + 1;
        // for (int pile : piles) hours += (int) Math.ceil((double) pile /speed);
        return hours <= h;
    }

    // fastest yet didn't understand left and right
    public int minEatingSpeed1(int[] piles, int h) {
        long sumPiles = 0;
        for (int pile : piles) sumPiles += pile;
        int left = (int) ((sumPiles - 1) / h) + 1;
        int right = (int) ((sumPiles - piles.length) / (h - piles.length + 1) + 1);
        /* slow alternative
        int maxPile = 0;
        for (int b : piles) maxPile = Math.max(maxPile, b); // return max
        int right = maxPile;*/

        while (left < right) {
            int speed = left + (right - left) / 2;
            int hours = 0;
            for (int pile : piles) hours += (pile - 1) / speed + 1;
            if (hours <= h) right = speed;
            else left = speed + 1;
        }
        return left;
    }

    public static void test() {
        MinEatingSpeed m = new MinEatingSpeed();
        assert m.minEatingSpeed(new int[]{3, 6, 7, 11}, 8) == 4;
    }
}

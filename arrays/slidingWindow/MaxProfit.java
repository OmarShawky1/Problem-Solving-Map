package arrays.slidingWindow;

public class MaxProfit {
    public int maxProfit1(int[] prices) {
        int prof = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int price : prices) {
            if (price < min) {
                min = price;
                max = Integer.MIN_VALUE; // Reset value
            } else {
                max = Math.max(max, price);
                prof = Math.max(prof, max - min);
            }
        }
        return prof;
    }

    public int maxProfit(int[] prices) {
        int prof = 0, max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            prof = Math.max(prof, max - prices[i]);
            max = Math.max(max, prices[i]);
        }
        return prof;
    }

    public static void test() {
        MaxProfit m = new MaxProfit();
        assert m.maxProfit(new int[]{7, 1, 5, 3, 6, 4}) == 5;
        assert m.maxProfit(new int[]{7, 6, 4, 3, 1}) == 0;
        assert m.maxProfit(new int[]{3,2,6,5,0,3}) == 4;
    }
}

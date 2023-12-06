package arrays.prefixSum;

import java.util.Arrays;

public class ProductExceptSelf {

    // Frequency Array. Slow.
    public int[] productExceptSelf1(int[] nums) {
        int[] freq = new int[61];
        for (int i : nums) freq[i + 30]++;

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = 1;
            for (int j = -30; j < 30; j++) {
                if (j == nums[i]) ans[i] *= (int) Math.pow(j,  freq[j + 30] - 1);
                else ans[i] *= (int) Math.pow(j,  freq[j + 30]);
                if (ans[i] == 0) break;
            }
        }
        return ans;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] pre = new int[n];
        pre[0] = 1;
        for(int i = 1; i < n; i++) pre[i] = pre[i - 1] * nums[i - 1];

        int[] suf = new int[n];
        suf[n - 1] = 1;
        for(int i = n - 2; i >= 0; i--) suf[i] = suf[i + 1] * nums[i + 1];

        int[] ans = new int[n];
        for(int i = 0; i < n; i++) ans[i] = pre[i] * suf[i];
        return ans;
    }

    public static void test() {
        ProductExceptSelf p = new ProductExceptSelf();
        assert Arrays.equals(p.productExceptSelf(new int[]{1, 2, 3, 4}), new int[]{24, 12, 8, 6});
        assert Arrays.equals(p.productExceptSelf(new int[]{-1, 1, 0, -3, 3}), new int[]{0, 0, 9, 0, 0});
        assert Arrays.equals(p.productExceptSelf(new int[]{5, 9, 2, -9, -9, -7, -8, 7, -9, 10}),
                new int[]{-51438240, -28576800, -128595600, 28576800, 28576800, 36741600, 32148900, -36741600, 28576800, -25719120});
    }
}

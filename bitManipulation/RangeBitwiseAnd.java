package bitManipulation;

public class RangeBitwiseAnd {

    /*public int rangeBitwiseAnd(int left, int right) {
        int ans = left;
        for (;left <= right && left <= Integer.MAX_VALUE - 1; left++) {
            ans &= left;
            if (ans == 0) break;
        }
        return ans;
    }*/

    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left << shift;
    }

    public static void test() {
        RangeBitwiseAnd r = new RangeBitwiseAnd();
        assert r.rangeBitwiseAnd(5, 7) == 4;
        assert r.rangeBitwiseAnd(0, 0) == 0;
        assert r.rangeBitwiseAnd(1, 2147483647) == 0;
        assert r.rangeBitwiseAnd(32, 32) == 32;
        assert r.rangeBitwiseAnd(2147483646,2147483647) == 2147483646;
    }
}

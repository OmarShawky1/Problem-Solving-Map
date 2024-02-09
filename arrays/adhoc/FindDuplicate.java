package arrays.adhoc;

public class FindDuplicate {

    // Most Maintainable
    // Floyd's Hare Tortoise Algorithm
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    // Fastest but only within small constrains as length is < 10^7
    public int findDuplicate1(int[] nums) {
        boolean[] booleans = new boolean[nums.length];
        int res = 0;
        for (int i: nums) {
            if (booleans[i]) {
                res = i;
                break;
            }
            booleans[i] = true;
        }
        return res;
    }

    public static void test() {
        FindDuplicate f = new FindDuplicate();
        assert f.findDuplicate(new int[]{1, 3, 4, 2, 2}) == 2;
    }
}

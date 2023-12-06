package arrays.slidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ContainsNearbyDuplicate {

    // Brute force
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int numsI = nums[i];
            for (int j = i + 1; (j - 1 < i + k) && (j < nums.length); j++) {
                if (numsI == nums[j]) return true;
            }
        }
        return false;
    }

    // Using Hashmap
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int numsI = nums[i];
            if (map.containsKey(numsI) && Math.abs(map.get(numsI) - i) <= k) return true;
            map.put(nums[i], i);
        }
        return false;
    }

    // Using HashSet (faster than HashMap)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i - k - 1]);
            if (!set.add(nums[i])) return true;
        }
        return false;
    }


    public static void test() {
        ContainsNearbyDuplicate c = new ContainsNearbyDuplicate();
        assert c.containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3);
        assert c.containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1);
        assert !c.containsNearbyDuplicate(new int[]{5, 6, 7, 9, 10, 5}, 2);
        assert !c.containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2);
    }
}

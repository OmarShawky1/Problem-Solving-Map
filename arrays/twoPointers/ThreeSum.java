package arrays.twoPointers;

import java.util.*;
import java.util.stream.Collectors;

public class ThreeSum {

    private int[] nums;

    // Brute Force
    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums); // Sort the array to efficiently handle duplicates
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        // Brute force approach: Try all possible combinations
        for (int i = 0; i < n - 2; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 1; j++) {
                // Skip duplicates for the second element
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                for (int k = j + 1; k < n; k++) {
                    // Skip duplicates for the third element
                    if (k > j + 1 && nums[k] == nums[k - 1]) continue;

                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        triplet.add(nums[k]);
                        result.add(triplet);
                    }
                }
            }
        }
        return result;
    }

    // Two Pointer Solution
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        ArrayList<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue; // Avoid repetition
            twoSum(i, ans);
        }
        return ans;
    }

    private void twoSum(int i, ArrayList<List<Integer>> ans) {
        int target = -nums[i], left = i + 1, right = nums.length - 1;

        while (left < right) {
            //Skip repeating second number
            if (i + 1 < left && nums[left - 1] == nums[left]) {
                left++;
                continue;
            }
            /* Alternative for left
            if (right != nums.length - 1 && nums[right] == nums[right + 1]) {
                right--;
                continue;
            }
            */

            if (nums[left] + nums[right] > target) right--;
            else if (nums[left] + nums[right] < target) left++;
                // else if (nums[left] + nums[right] == target) { //redundant check
            else { //redundant check
                ArrayList<Integer> l = new ArrayList<>(3); // 3 is the size of list
                l.add(nums[i]);
                l.add(nums[left]);
                l.add(nums[right]);
                ans.add(l);
                left++; // move to next pair
                // right--; // move to next pair
            }
        }
    }

    // Second Fastest
    // Hash Tables and Anonymous Abstract List implementation (get, size and init) for compiler optimization.
    // import java.util.*; // Use it when submitting
    class Solution1 {
        private List<List<Integer>> res;
        public List<List<Integer>> threeSum(int[] nums) {
            return new AbstractList<List<Integer>>() {
                public List<Integer> get(int index) {
                    init();
                    return res.get(index);
                }
                public int size() {
                    init();
                    return res.size();
                }
                private void init() {
                    if (res != null) return;
                    Arrays.sort(nums);
                    int l, r, sum;
                    Set<List<Integer>> tempRes = new HashSet<>();
                    for(int i = 0; i < nums.length - 2; ++i) {
                        l = i + 1;
                        r = nums.length - 1;
                        while(l < r) {
                            sum = nums[i] + nums[l] + nums[r];
                            if (sum == 0) tempRes.add(Arrays.asList(nums[i], nums[l], nums[r]));
                            if (sum < 0) ++l; else --r;
                        }
                    }
                    res = new ArrayList<List<Integer>>(tempRes);
                }

            };
        }
    }

    // Fastest possible solution, 0 time.
    // Two Pointers and Anonymous Abstract List implementation (get, size and init) for compiler optimization
    // import java.util.*; // Use it when submitting
    class Solution2 {
        private List<List<Integer>> res;

        public List<List<Integer>> threeSum(int[] nums) {
            return new AbstractList<List<Integer>>() {

                public List<Integer> get(int index) {
                    init();
                    return res.get(index);
                }

                public int size() {
                    init();
                    return res.size();
                }

                private void init() {
                    if (res != null) return;
                    Arrays.sort(nums);
                    ArrayList<List<Integer>> ans = new ArrayList<>();

                    for (int i = 0; i < nums.length; i++) {
                        if (i != 0 && nums[i] == nums[i - 1]) continue; // Avoid repetition
                        twoSum(i, ans);
                    }
                    res = ans;
                }

                private void twoSum(int i, ArrayList<List<Integer>> ans) {
                    int target = -nums[i], left = i + 1, right = nums.length - 1;

                    while (left < right) {
                        //Skip repeating second number
                        if (i + 1 < left && nums[left - 1] == nums[left]) {
                            left++;
                            continue;
                        }
                        if (nums[left] + nums[right] > target) right--;
                        else if (nums[left] + nums[right] < target) left++;
                        else { //redundant check
                            ArrayList<Integer> l = new ArrayList<>(3); // 3 is the size of list
                            l.add(nums[i]);
                            l.add(nums[left]);
                            l.add(nums[right]);
                            ans.add(l);
                            left++; // move to next pair
                        }
                    }
                }
            };
        }
    }

    public static void test() {
        ThreeSum t = new ThreeSum();
        List<List<Integer>> temp1 = t.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for (int i = 0; i < temp1.get(0).size(); i++) {
            int[] tempSol = new int[]{-1, 0, 1};
            assert temp1.get(1).get(i) == tempSol[i];
        }

        for (int i = 0; i < temp1.get(0).size(); i++) {
            int[] tempSol = new int[]{-1, -1, 2};
            assert temp1.get(0).get(i) == tempSol[i];
        }

        assert t.threeSum(new int[]{0, 1, 1}).isEmpty();

        List<List<Integer>> temp2 = t.threeSum(new int[]{0, 0, 0});
        for (int i = 0; i < temp1.get(0).size(); i++) {
            int[] tempSol = new int[]{0, 0, 0};
            assert temp2.get(0).get(i) == tempSol[i];
        }

        List<List<Integer>> temp3 = t.threeSum(new int[]{0, 0, 0, 0});
        for (int i = 0; i < temp1.get(0).size(); i++) {
            int[] tempSol = new int[]{0, 0, 0};
            assert temp3.get(0).get(i) == tempSol[i];
        }
    }
}

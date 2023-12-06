package arrays.sortingAndSearching;

import java.util.*;

/**
 * Return descending list of size k of entries elements' frequencies
 * Problem Link: <a href="https://leetcode.com/problems/top-k-frequent-elements/description/">...</a>
 */
public class TopKFrequent {

    // Brute Force
    public int[] topKFrequent1(int[] nums, int k) {
        // 1. Count the frequency of each element in the input list
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) counts.put(num, counts.getOrDefault(num, 0) + 1);

        // 2. Sort in descending order based on the counts
        List<Map.Entry<Integer, Integer>> countsList = new ArrayList<>(counts.entrySet());
        countsList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 3. Slice the first k elements to build the result list
        Map<Integer, Integer> sortedCounts = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : countsList.subList(0, k))
            sortedCounts.put(entry.getKey(), entry.getValue());

        return sortedCounts.keySet().stream().mapToInt(Integer::intValue).toArray();
    }

    // Least Optimal
    public int[] topKFrequent2(int[] nums, int k) {
        int constant = 10000;
        for (int i = 0; i < nums.length; i++) nums[i] += constant;
        Integer[] freq = new Integer[20001];
        Arrays.fill(freq, 0);
        for (int i : nums) freq[i]++;

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(freq[nums[i]])) map.put(freq[nums[i]], new HashSet<>());
            map.get(freq[nums[i]]).add(nums[i]);
        }

        Arrays.sort(freq, Comparator.reverseOrder());

        int[] ans = new int[k];
        int j = 0;
        while (j < k) {
            for (int n : map.get(freq[j])) {
                ans[j] = n - constant;
                j++;
                if (j >= k) break;
            }
        }
        return ans;
    }

    // Frequency array using Array List. Second fast.
    public int[] topKFrequent3(int[] nums, int k) {
        int[] freqs = new int[20001];
        List<Integer> numsWithFreq = new ArrayList<>();
        for (int i : nums) if (++freqs[i + 10000] == 1) numsWithFreq.add(i);

        Collections.sort(numsWithFreq, (a, b) -> freqs[10000 + b] - freqs[10000 + a]);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) result[i] = numsWithFreq.get(i);

        return result;
    }

    // Frequency array using priority Queue. Fastest.
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(
                (a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
        int[] freq = new int[20001];
        for (int num : nums) freq[num + 10000]++;

        for (int i = 0; i < 20001; i++) if (freq[i] != 0) priorityQueue.add(new int[]{i - 10000, freq[i]});
        int[] result = new int[Math.min(priorityQueue.size(), k)];
        for (int i = 0; i < result.length; i++) result[i] = priorityQueue.poll()[0];
        return result;
    }


    public static void test() {
        TopKFrequent t = new TopKFrequent();
        assert Arrays.equals(t.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2), new int[]{1, 2});
        assert Arrays.equals(t.topKFrequent(new int[]{1}, 1), new int[]{1});
        assert Arrays.equals(t.topKFrequent(new int[]{1, 2}, 2), new int[]{1, 2});
    }
}

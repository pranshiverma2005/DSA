import java.util.*;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        // Step 1: Put all numbers in a HashSet for O(1) lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Step 2: Start checking multiples of k
        int multiple = k;
        while (true) {
            if (!set.contains(multiple)) {
                return multiple; // First missing multiple found
            }
            multiple += k;
        }
    }
}

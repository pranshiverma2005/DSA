class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean valid = true;

            for (int start = 0; start < n && valid; start += k) {
                int descents = 0;

                for (int j = start; j < start + k; j++) {
                    int next = (j + 1 == start + k) ? start : j + 1;

                    if (nums[j] > nums[next]) {
                        descents++;
                    }
                }

                if (descents > 1) {
                    valid = false;
                }
            }

            if (valid) {
                // A rotation of each block must also preserve
                // non-decreasing order between adjacent blocks.
                int prevMax = Integer.MIN_VALUE;

                for (int start = 0; start < n; start += k) {
                    int min = Integer.MAX_VALUE;
                    int max = Integer.MIN_VALUE;

                    for (int j = start; j < start + k; j++) {
                        min = Math.min(min, nums[j]);
                        max = Math.max(max, nums[j]);
                    }

                    if (min < prevMax) {
                        valid = false;
                        break;
                    }

                    prevMax = max;
                }
            }

            if (valid) ans += k;
        }

        return ans;
    }
}
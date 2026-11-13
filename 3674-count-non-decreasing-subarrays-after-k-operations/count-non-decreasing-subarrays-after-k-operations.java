
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int LOG = 17;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Find next strictly greater element
        int[] ng = new int[n];
        int[] st = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[st[top]] <= nums[i]) {
                top--;
            }

            ng[i] = (top == -1) ? -1 : st[top];
            st[++top] = i;
        }

        // Binary lifting tables
        int[][] jump = new int[LOG][n];
        long[][] cost = new long[LOG][n];

        for (int i = 0; i < n; i++) {
            jump[0][i] = ng[i];

            if (ng[i] != -1) {
                cost[0][i] =
                    (long) nums[i] * (ng[i] - i)
                    - (prefix[ng[i]] - prefix[i]);
            }
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                int mid = jump[j - 1][i];

                if (mid != -1) {
                    jump[j][i] = jump[j - 1][mid];

                    if (jump[j][i] != -1) {
                        cost[j][i] =
                            cost[j - 1][i] + cost[j - 1][mid];
                    }
                } else {
                    jump[j][i] = -1;
                }
            }
        }

        // Calculate operations needed for [l, r]
        long ans = 0;
        int right = -1;

        for (int left = 0; left < n; left++) {

            while (right + 1 < n &&
                   getCost(left, right + 1, nums,
                           prefix, jump, cost, LOG) <= k) {
                right++;
            }

            ans += right - left + 1;
        }

        return ans;
    }

    private long getCost(
        int l, int r,
        int[] nums,
        long[] prefix,
        int[][] jump,
        long[][] cost,
        int LOG
    ) {
        int pos = l;
        long total = 0;

        for (int j = LOG - 1; j >= 0; j--) {
            int nxt = jump[j][pos];

            if (nxt != -1 && nxt <= r) {
                total += cost[j][pos];
                pos = nxt;
            }
        }

        // Remaining final block
        total += (long) nums[pos] * (r - pos + 1)
                 - (prefix[r + 1] - prefix[pos]);

        return total;
    }
}

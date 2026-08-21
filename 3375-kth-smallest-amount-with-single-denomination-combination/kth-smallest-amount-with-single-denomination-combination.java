import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);
        long left = 1, right = (long)1e18;
        long ans = -1;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long count = countDistinct(coins, mid);

            if (count >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private long countDistinct(int[] coins, long x) {
        int n = coins.length;
        long total = 0;

        // Inclusion-Exclusion over subsets
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    lcm = lcm(lcm, coins[i]);
                    if (lcm > x) { overflow = true; break; }
                }
            }

            if (!overflow) {
                int bits = Integer.bitCount(mask);
                if (bits % 2 == 1) total += x / lcm;
                else total -= x / lcm;
            }
        }
        return total;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}


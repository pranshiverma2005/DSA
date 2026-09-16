class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {

        long[][] dp = new long[n][k + 1];

        // 0 segments: exactly 1 way
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int segments = 1; segments <= k; segments++) {

            long sum = 0;

            for (int i = 1; i < n; i++) {

                // dp[i-1][segments-1] can be the
                // configuration before starting
                // the current segment.
                sum = (sum + dp[i - 1][segments - 1]) % MOD;

                dp[i][segments] =
                    (dp[i - 1][segments] + sum) % MOD;
            }
        }

        return (int) dp[n - 1][k];
    }
}
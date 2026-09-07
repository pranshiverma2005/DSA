class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;

        long[] last = new long[26];

        long dp = 1; // empty subsequence

        for (char c : s.toCharArray()) {
            int idx = c - 'a';

            long newDp = (2 * dp % MOD - last[idx] + MOD) % MOD;

            // Store the contribution before this character was processed
            last[idx] = dp;

            dp = newDp;
        }

        // Remove the empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}
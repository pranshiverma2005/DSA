
class Solution {
    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        long[][][] dp = new long[m][n][2];

        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Destination reached
                if (i == m - 1 && j == n - 1) {
                    return (int)((dp[i][j][0] +
                                  dp[i][j][1]) % MOD);
                }

                if (grid[i][j] == 0) {

                    // Empty cell
                    long ways = (dp[i][j][0] +
                                 dp[i][j][1]) % MOD;

                    // Move right
                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                            (dp[i][j + 1][0] + ways) % MOD;
                    }

                    // Move down
                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                            (dp[i + 1][j][1] + ways) % MOD;
                    }

                } else {

                    // Mirror cell

                    // Entered from left -> reflect DOWN
                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                            (dp[i + 1][j][1] +
                             dp[i][j][0]) % MOD;
                    }

                    // Entered from above -> reflect RIGHT
                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                            (dp[i][j + 1][0] +
                             dp[i][j][1]) % MOD;
                    }
                }
            }
        }

        return 0;
    }
}

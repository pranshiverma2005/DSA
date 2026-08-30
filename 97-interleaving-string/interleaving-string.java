class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {

        int n = s1.length();
        int m = s2.length();

        // Length must match
        if (n + m != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[n + 1][m + 1];

        // Empty strings form an empty string
        dp[0][0] = true;

        for (int i = 0; i <= n; i++) {

            for (int j = 0; j <= m; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                // Take character from s1
                if (i > 0 &&
                    s1.charAt(i - 1) == s3.charAt(i + j - 1)) {

                    dp[i][j] = dp[i - 1][j];
                }

                // Take character from s2
                if (j > 0 &&
                    s2.charAt(j - 1) == s3.charAt(i + j - 1)) {

                    dp[i][j] =
                        dp[i][j] || dp[i][j - 1];
                }
            }
        }

        return dp[n][m];
    }
}
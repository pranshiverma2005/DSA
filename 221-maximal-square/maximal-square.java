class Solution {
    public int maximalSquare(char[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        int maxSide = 0;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // If current cell is 0,
                // no square can end here
                if (matrix[i][j] == '0') {

                    dp[i][j] = 0;

                } else {

                    // First row or first column
                    if (i == 0 || j == 0) {

                        dp[i][j] = 1;

                    } else {

                        dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],
                            Math.min(
                                dp[i][j - 1],
                                dp[i - 1][j - 1]
                            )
                        );
                    }

                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }
}
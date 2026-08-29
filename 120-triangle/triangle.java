class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();

        // dp contains the minimum path sums
        // for the current row
        int[] dp = new int[n];

        // Start with the bottom row
        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }

        // Move from second-last row to the top
        for (int i = n - 2; i >= 0; i--) {

            for (int j = 0; j <= i; j++) {

                dp[j] = triangle.get(i).get(j)
                        + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }
}
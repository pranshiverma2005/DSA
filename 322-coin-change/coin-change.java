import java.util.*;

class Solution {
    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount + 1];

        // Impossible value
        Arrays.fill(dp, amount + 1);

        // 0 coins needed for amount 0
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {

            for (int coin : coins) {

                if (i - coin >= 0) {
                    dp[i] = Math.min(
                        dp[i],
                        dp[i - coin] + 1
                    );
                }
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
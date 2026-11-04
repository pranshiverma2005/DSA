
import java.util.*;

class Solution {

    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    long leftSum = 0;
    long rightSum = 0;

    int leftSize = 0;
    int rightSize = 0;

    void addLeft(int num) {
        left.put(num, left.getOrDefault(num, 0) + 1);
        leftSum += num;
        leftSize++;
    }

    void addRight(int num) {
        right.put(num, right.getOrDefault(num, 0) + 1);
        rightSum += num;
        rightSize++;
    }

    void removeLeft(int num) {
        int count = left.get(num);

        if (count == 1)
            left.remove(num);
        else
            left.put(num, count - 1);

        leftSum -= num;
        leftSize--;
    }

    void removeRight(int num) {
        int count = right.get(num);

        if (count == 1)
            right.remove(num);
        else
            right.put(num, count - 1);

        rightSum -= num;
        rightSize--;
    }

    void rebalance(int windowSize) {

        int target = (windowSize + 1) / 2;

        while (leftSize > target) {
            int num = left.lastKey();
            removeLeft(num);
            addRight(num);
        }

        while (leftSize < target) {
            int num = right.firstKey();
            removeRight(num);
            addLeft(num);
        }
    }

    void add(int num, int windowSize) {

        if (leftSize == 0 || num <= left.lastKey())
            addLeft(num);
        else
            addRight(num);

        rebalance(windowSize);
    }

    void remove(int num, int windowSize) {

        if (left.containsKey(num))
            removeLeft(num);
        else
            removeRight(num);

        rebalance(windowSize);
    }

    long getCost() {

        long median = left.lastKey();

        return median * leftSize - leftSum
             + rightSum - median * rightSize;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        // Step 1: Calculate sliding window costs

        for (int i = 0; i < x; i++) {
            add(nums[i], i + 1);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {

            remove(nums[i - x], x - 1);
            add(nums[i], x);

            cost[i - x + 1] = getCost();
        }

        // Step 2: Dynamic Programming

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                // Option 1: Skip current element
                dp[i][j] = dp[i - 1][j];

                // Option 2: Select window of size x
                if (i >= x && dp[i - x][j - 1] != INF) {

                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + cost[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }
}

import java.util.*;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        // Min heap based on required capital
        PriorityQueue<int[]> minCapital =
            new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Max heap based on profit
        PriorityQueue<Integer> maxProfit =
            new PriorityQueue<>((a, b) -> b - a);

        // Add all projects
        for (int i = 0; i < profits.length; i++) {
            minCapital.offer(new int[]{capital[i], profits[i]});
        }

        // Choose at most k projects
        for (int i = 0; i < k; i++) {

            // Add all affordable projects
            while (!minCapital.isEmpty()
                    && minCapital.peek()[0] <= w) {

                maxProfit.offer(minCapital.poll()[1]);
            }

            // No project can be started
            if (maxProfit.isEmpty()) {
                break;
            }

            // Take maximum profit project
            w += maxProfit.poll();
        }

        return w;
    }
}
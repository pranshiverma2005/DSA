class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int[] best = new int[n];

        Arrays.fill(best, Integer.MAX_VALUE);

        int left = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Shrink window if sum becomes greater than target
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // Carry forward the previous best
            if (right > 0) {
                best[right] = best[right - 1];
            }

            // Found a subarray with sum = target
            if (sum == target) {

                int currentLength = right - left + 1;

                // Check if there is a previous non-overlapping subarray
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    answer = Math.min(
                        answer,
                        currentLength + best[left - 1]
                    );
                }

                // Update best subarray ending at or before right
                best[right] = Math.min(
                    best[right],
                    currentLength
                );
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
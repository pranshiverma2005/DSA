class Solution {
    public int longestOnes(int[] nums, int k) {

        int left = 0;
        int zeros = 0;
        int max = 0;

        for (int right = 0; right < nums.length; right++) {

            // Add new element
            if (nums[right] == 0) {
                zeros++;
            }

            // Too many zeros → shrink window
            while (zeros > k) {

                if (nums[left] == 0) {
                    zeros--;
                }

                left++;
            }

            // Current window is valid
            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}
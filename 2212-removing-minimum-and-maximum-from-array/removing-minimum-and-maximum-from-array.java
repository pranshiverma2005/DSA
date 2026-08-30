class Solution {
    public int minimumDeletions(int[] nums) {

        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find positions of minimum and maximum
        for (int i = 0; i < n; i++) {

            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // left = smaller index
        // right = larger index
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        // Case 1: remove from front
        int fromFront = right + 1;

        // Case 2: remove from back
        int fromBack = n - left;

        // Case 3: remove from both sides
        int fromBoth = (left + 1) + (n - right);

        return Math.min(fromFront,
               Math.min(fromBack, fromBoth));
    }
}
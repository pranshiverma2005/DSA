class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search on the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m;

        int leftSize = (m + n + 1) / 2;

        while (low <= high) {

            int partition1 = low + (high - low) / 2;
            int partition2 = leftSize - partition1;

            int nums1Left;
            int nums1Right;
            int nums2Left;
            int nums2Right;

            // Values around partition1
            if (partition1 == 0) {
                nums1Left = Integer.MIN_VALUE;
            } else {
                nums1Left = nums1[partition1 - 1];
            }

            if (partition1 == m) {
                nums1Right = Integer.MAX_VALUE;
            } else {
                nums1Right = nums1[partition1];
            }

            // Values around partition2
            if (partition2 == 0) {
                nums2Left = Integer.MIN_VALUE;
            } else {
                nums2Left = nums2[partition2 - 1];
            }

            if (partition2 == n) {
                nums2Right = Integer.MAX_VALUE;
            } else {
                nums2Right = nums2[partition2];
            }

            // Correct partition
            if (nums1Left <= nums2Right &&
                nums2Left <= nums1Right) {

                // Total length is even
                if ((m + n) % 2 == 0) {

                    int leftMax = Math.max(nums1Left, nums2Left);
                    int rightMin = Math.min(nums1Right, nums2Right);

                    return ((double) leftMax + rightMin) / 2.0;

                } else {

                    // Total length is odd
                    return Math.max(nums1Left, nums2Left);
                }
            }

            // Too many elements taken from nums1
            else if (nums1Left > nums2Right) {
                high = partition1 - 1;
            }

            // Too few elements taken from nums1
            else {
                low = partition1 + 1;
            }
        }

        return 0.0;
    }
}
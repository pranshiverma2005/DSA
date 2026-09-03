class Solution {
    public boolean uniformArray(int[] nums1) {
         int smallestOdd = Integer.MAX_VALUE;

        // Find the smallest odd number
        for (int x : nums1) {
            if (x % 2 != 0) {
                smallestOdd = Math.min(smallestOdd, x);
            }
        }

        // No odd numbers -> already all even
        if (smallestOdd == Integer.MAX_VALUE) {
            return true;
        }

        // Every even number must be greater than
        // the smallest odd number.
        for (int x : nums1) {
            if (x % 2 == 0 && x < smallestOdd) {
                return false;
            }
        }

        return true;
    }
}
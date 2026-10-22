import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // State (orientation, shift):
        // orientation 0: left-rotate [0, 1, ..., n - 1] by shift
        // orientation 1: left-rotate [n - 1, ..., 1, 0] by shift
        int targetOrientation = -1;
        int targetShift = -1;

        // Match nums against rotations of the increasing array.
        int shift = nums[0];
        boolean matchesAscending = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (shift + i) % n) {
                matchesAscending = false;
                break;
            }
        }
        if (matchesAscending) {
            targetOrientation = 0;
            targetShift = shift;
        }

        // Match nums against rotations of the reversed array.
        shift = (n - 1 - nums[0] + n) % n;
        boolean matchesDescending = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != n - 1 - ((shift + i) % n)) {
                matchesDescending = false;
                break;
            }
        }
        if (matchesDescending && targetOrientation == -1) {
            targetOrientation = 1;
            targetShift = shift;
        }

        if (targetOrientation == -1) {
            return -1;
        }

        // BFS from nums to the sorted state (orientation 0, shift 0).
        boolean[][] seen = new boolean[2][n];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{targetOrientation, targetShift, 0});
        seen[targetOrientation][targetShift] = true;

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int orientation = state[0];
            int currentShift = state[1];
            int distance = state[2];

            if (orientation == 0 && currentShift == 0) {
                return distance;
            }

            // Rotate left by one.
            int rotatedShift = (currentShift + 1) % n;
            if (!seen[orientation][rotatedShift]) {
                seen[orientation][rotatedShift] = true;
                queue.offer(new int[]{orientation, rotatedShift, distance + 1});
            }

            // Reverse the entire array.
            int reversedOrientation = 1 - orientation;
            int reversedShift = (n - currentShift) % n;
            if (!seen[reversedOrientation][reversedShift]) {
                seen[reversedOrientation][reversedShift] = true;
                queue.offer(new int[]{reversedOrientation, reversedShift, distance + 1});
            }
        }

        return -1;
    }
}
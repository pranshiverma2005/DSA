import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            map.computeIfAbsent(seat[0], k -> new HashSet<>()).add(seat[1]);
        }

        int result = 2 * n;

        for (Set<Integer> seats : map.values()) {

            boolean left = true;
            boolean middle = true;
            boolean right = true;

            for (int seat : seats) {
                if (seat >= 2 && seat <= 5) {
                    left = false;
                }

                if (seat >= 4 && seat <= 7) {
                    middle = false;
                }

                if (seat >= 6 && seat <= 9) {
                    right = false;
                }
            }

            if (left && right) {
                // Already counted 2
                continue;
            } 
            else if (left || middle || right) {
                // Only 1 group possible
                result--;
            } 
            else {
                // No group possible
                result -= 2;
            }
        }

        return result;
    }
}
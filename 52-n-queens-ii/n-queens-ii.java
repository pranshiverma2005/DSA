import java.util.*;

class Solution {

    public int totalNQueens(int n) {

        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonal1 = new HashSet<>();
        Set<Integer> diagonal2 = new HashSet<>();

        return backtrack(0, n, columns, diagonal1, diagonal2);
    }

    private int backtrack(int row,
                           int n,
                           Set<Integer> columns,
                           Set<Integer> diagonal1,
                           Set<Integer> diagonal2) {

        // All queens placed
        if (row == n) {
            return 1;
        }

        int count = 0;

        // Try every column
        for (int col = 0; col < n; col++) {

            // Check if queen is attacked
            if (columns.contains(col) ||
                diagonal1.contains(row - col) ||
                diagonal2.contains(row + col)) {

                continue;
            }

            // Choose
            columns.add(col);
            diagonal1.add(row - col);
            diagonal2.add(row + col);

            // Explore
            count += backtrack(
                row + 1,
                n,
                columns,
                diagonal1,
                diagonal2
            );

            // Undo
            columns.remove(col);
            diagonal1.remove(row - col);
            diagonal2.remove(row + col);
        }

        return count;
    }
}
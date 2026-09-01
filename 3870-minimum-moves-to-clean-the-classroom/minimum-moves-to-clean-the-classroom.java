import java.util.*;

class Solution {

    static class State {
        int r, c;
        int energy;
        int mask;
        int moves;

        State(int r, int c, int energy, int mask, int moves) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
            this.moves = moves;
        }
    }

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0, startC = 0;

        // Give every litter a bit number
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int allCollected = (1 << litterCount) - 1;

        // visited[r][c][energy][mask]
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litterCount];

        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(
            startR,
            startC,
            energy,
            0,
            0
        ));

        visited[startR][startC][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            State curr = queue.poll();

            // All litter collected
            if (curr.mask == allCollected) {
                return curr.moves;
            }

            // No energy left and we're not on R
            if (curr.energy == 0) {
                continue;
            }

            for (int d = 0; d < 4; d++) {

                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                int newEnergy = curr.energy - 1;

                int newMask = curr.mask;

                // Collect litter
                if (classroom[nr].charAt(nc) == 'L') {
                    int id = litterId[nr][nc];
                    newMask |= (1 << id);
                }

                // Reset energy on R
                if (classroom[nr].charAt(nc) == 'R') {
                    newEnergy = energy;
                }

                if (!visited[nr][nc][newEnergy][newMask]) {

                    visited[nr][nc][newEnergy][newMask] = true;

                    queue.offer(new State(
                        nr,
                        nc,
                        newEnergy,
                        newMask,
                        curr.moves + 1
                    ));
                }
            }
        }

        return -1;
    }
}
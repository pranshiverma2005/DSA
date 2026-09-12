import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    Interval[] arr;
    int n;
    State[][] memo;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();
        arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                    intervals.get(i).get(0),
                    intervals.get(i).get(1),
                    intervals.get(i).get(2),
                    i
            );
        }

        // Sort by starting position
        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);
            return Integer.compare(a.r, b.r);
        });

        memo = new State[n + 1][5];

        State ans = solve(0, 4);

        int[] result = new int[ans.indices.size()];
        for (int i = 0; i < ans.indices.size(); i++) {
            result[i] = ans.indices.get(i);
        }

        Arrays.sort(result);
        return result;
    }

    private State solve(int i, int k) {

        // No intervals left or cannot choose anymore
        if (i == n || k == 0) {
            return new State(0, new ArrayList<>());
        }

        if (memo[i][k] != null) {
            return memo[i][k];
        }

        // Option 1: skip current interval
        State skip = solve(i + 1, k);

        // Option 2: take current interval
        int next = findNext(i);

        State takeNext = solve(next, k - 1);

        List<Integer> takeIndices = new ArrayList<>();
        takeIndices.add(arr[i].idx);
        takeIndices.addAll(takeNext.indices);

        long takeScore = arr[i].w + takeNext.score;

        State take = new State(takeScore, takeIndices);

        // Choose the better option
        State best;

        if (take.score > skip.score) {
            best = take;
        } 
        else if (take.score < skip.score) {
            best = skip;
        } 
        else {
            // Same score -> lexicographically smaller indices
            best = lexicographicallySmaller(take, skip);
        }

        memo[i][k] = best;
        return best;
    }

    // Find first interval whose starting point is > current ending point
    private int findNext(int i) {

        int target = arr[i].r + 1;

        int lo = i + 1;
        int hi = n;

        while (lo < hi) {

            int mid = lo + (hi - lo) / 2;

            if (arr[mid].l >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private State lexicographicallySmaller(State a, State b) {

        List<Integer> x = new ArrayList<>(a.indices);
        List<Integer> y = new ArrayList<>(b.indices);

        Collections.sort(x);
        Collections.sort(y);

        int len = Math.min(x.size(), y.size());

        for (int i = 0; i < len; i++) {

            if (!x.get(i).equals(y.get(i))) {
                return x.get(i) < y.get(i) ? a : b;
            }
        }

        // If one is a prefix of the other,
        // shorter array is lexicographically smaller.
        return x.size() <= y.size() ? a : b;
    }
}
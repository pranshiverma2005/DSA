class Solution {

    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> result = new ArrayList<>();

        backtrack(1, k, n, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(
        int start,
        int k,
        int target,
        int sum,
        List<Integer> path,
        List<List<Integer>> result
    ) {

        // Exactly k numbers selected
        if (path.size() == k) {

            if (sum == target) {
                result.add(new ArrayList<>(path));
            }

            return;
        }

        // Try numbers from start to 9
        for (int i = start; i <= 9; i++) {

            path.add(i);

            backtrack(
                i + 1,
                k,
                target,
                sum + i,
                path,
                result
            );

            // Backtrack
            path.remove(path.size() - 1);
        }
    }
}
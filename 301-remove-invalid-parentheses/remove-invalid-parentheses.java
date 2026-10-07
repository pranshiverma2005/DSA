class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> ans = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                // If valid, add it to answer
                if (isValid(current)) {
                    ans.add(current);
                    found = true;
                }

                // If valid strings found at this level,
                // don't generate strings with more removals
                if (found) {
                    continue;
                }

                // Remove one parenthesis at every possible position
                for (int j = 0; j < current.length(); j++) {

                    char ch = current.charAt(j);

                    // Only remove parentheses
                    if (ch != '(' && ch != ')') {
                        continue;
                    }

                    String next = current.substring(0, j)
                            + current.substring(j + 1);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

            // We found the minimum-removal answers
            if (found) {
                break;
            }
        }

        return ans;
    }

    private boolean isValid(String s) {

        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;
            } 
            else if (ch == ')') {
                balance--;
            }

            // More ')' than '('
            if (balance < 0) {
                return false;
            }
        }

        // Every '(' must have a ')'
        return balance == 0;
    }
}
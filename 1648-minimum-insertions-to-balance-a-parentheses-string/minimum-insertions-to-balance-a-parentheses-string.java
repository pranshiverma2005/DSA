
class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;

        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                open++;
                i++;
            } else {
                // Check whether the next character is also ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    // We found a pair '))'
                    i += 2;
                } else {
                    // Only one ')' exists; insert another ')'
                    insertions++;
                    i++;
                }

                // A pair '))' must match an opening '('
                if (open > 0) {
                    open--;
                } else {
                    // No opening bracket; insert '('
                    insertions++;
                }
            }
        }

        // Each remaining '(' needs two ')'
        insertions += open * 2;

        return insertions;
    }
}

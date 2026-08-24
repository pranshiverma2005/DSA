class Solution {
    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        if (digits.length() == 0) {
            return result;
        }

        String[] map = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };

        backtrack(0, digits, map, new StringBuilder(), result);

        return result;
    }

    private void backtrack(
        int index,
        String digits,
        String[] map,
        StringBuilder current,
        List<String> result
    ) {

        // All digits processed
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (char ch : letters.toCharArray()) {

            // choose
            current.append(ch);

            // explore
            backtrack(index + 1, digits, map, current, result);

            // undo choice
            current.deleteCharAt(current.length() - 1);
        }
    }
}
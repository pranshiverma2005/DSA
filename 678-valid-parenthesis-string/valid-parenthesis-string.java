class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                low++;
                high++;
            } 
            else if (c == ')') {
                low--;
                high--;
            } 
            else { // '*'
                low--;
                high++;
            }

            // Even the maximum possible '(' count is negative
            if (high < 0) {
                return false;
            }

            // We cannot have negative minimum
            if (low < 0) {
                low = 0;
            }
        }

        return low == 0;
    }
}
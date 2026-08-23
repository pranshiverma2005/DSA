class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumLeft = 0, sumRight = 0;
        int qLeft = 0, qRight = 0;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (i < n / 2) {
                if (c == '?') qLeft++;
                else sumLeft += c - '0';
            } else {
                if (c == '?') qRight++;
                else sumRight += c - '0';
            }
        }

        // If odd number of '?' → Alice wins
        if ((qLeft + qRight) % 2 == 1) return true;

        // Balance check
        int diff = sumLeft - sumRight;
        int qDiff = qRight - qLeft;

        // Each pair of '?' can adjust difference by 9
        return diff != qDiff * 9 / 2;
    }
}

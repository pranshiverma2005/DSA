class Solution {
    public int totalNumbers(int[] digits) {

        int[] freq = new int[10];

        // Count how many times each digit appears
        for (int digit : digits) {
            freq[digit]++;
        }

        int ans = 0;

        // Try every 3-digit number
        for (int num = 100; num <= 999; num++) {

            // Must be even
            if (num % 2 != 0) {
                continue;
            }

            int a = num / 100;        // hundreds
            int b = (num / 10) % 10;  // tens
            int c = num % 10;         // ones

            // Check whether we have enough copies
            int[] need = new int[10];
            need[a]++;
            need[b]++;
            need[c]++;

            boolean possible = true;

            for (int d = 0; d <= 9; d++) {
                if (need[d] > freq[d]) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                ans++;
            }
        }

        return ans;
    }
}
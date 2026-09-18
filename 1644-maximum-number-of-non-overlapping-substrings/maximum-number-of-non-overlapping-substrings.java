class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try to create a valid interval from every character
        for (int c = 0; c < 26; c++) {

            if (last[c] == -1)
                continue;

            int start = first[c];
            int end = last[c];

            boolean valid = true;

            for (int i = start; i <= end; i++) {

                int curr = s.charAt(i) - 'a';

                // Character appears before our start
                if (first[curr] < start) {
                    valid = false;
                    break;
                }

                // Must include all occurrences
                end = Math.max(end, last[curr]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> answer = new ArrayList<>();

        int prevEnd = -1;

        // Greedy interval scheduling
        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            if (start > prevEnd) {

                answer.add(s.substring(start, end + 1));

                prevEnd = end;
            }
        }

        return answer;
    }
}
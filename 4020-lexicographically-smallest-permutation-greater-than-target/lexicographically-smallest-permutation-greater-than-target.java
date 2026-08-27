class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {

            int targetChar = target.charAt(i) - 'a';

            if (freq[targetChar] > 0) {

                freq[targetChar]--;
                prefix.append((char) ('a' + targetChar));

            } else {

                int larger = findLarger(freq, targetChar);

                if (larger != -1) {
                    prefix.append((char) ('a' + larger));
                    freq[larger]--;

                    return prefix.toString() + buildSmallest(freq);
                }

                break;
            }
        }

        // Backtrack
        for (int i = prefix.length() - 1; i >= 0; i--) {

            int current = prefix.charAt(i) - 'a';
            freq[current]++;

            int targetChar = target.charAt(i) - 'a';

            int larger = findLarger(freq, targetChar);

            if (larger != -1) {

                prefix.setLength(i);
                prefix.append((char) ('a' + larger));
                freq[larger]--;

                return prefix.toString() + buildSmallest(freq);
            }
        }

        return "";
    }

    private int findLarger(int[] freq, int targetChar) {

        for (int c = targetChar + 1; c < 26; c++) {
            if (freq[c] > 0) {
                return c;
            }
        }

        return -1;
    }

    private String buildSmallest(int[] freq) {

        StringBuilder result = new StringBuilder();

        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                result.append((char) ('a' + c));
                freq[c]--;
            }
        }

        return result.toString();
    }
}
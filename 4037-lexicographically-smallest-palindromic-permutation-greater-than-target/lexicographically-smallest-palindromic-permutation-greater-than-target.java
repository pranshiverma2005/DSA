class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int halfLen = n / 2;
        int[] original = new int[26];

        for (char ch : s.toCharArray()) {
            original[ch - 'a']++;
        }

        int oddCount = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if ((original[i] & 1) == 1) {
                oddCount++;
                middle = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // Counts available for the left half.
        int[] count = new int[26];
        for (int i = 0; i < 26; i++) {
            count[i] = original[i] / 2;
        }

        String prefix = target.substring(0, halfLen);
        int pivot = -1;
        int matched = 0;

        // Match target's left half as long as possible.
        for (int i = 0; i < halfLen; i++) {
            int current = prefix.charAt(i) - 'a';

            // At this position, record whether we can increase the character.
            for (int ch = current + 1; ch < 26; ch++) {
                if (count[ch] > 0) {
                    pivot = i;
                    break;
                }
            }

            if (count[current] == 0) {
                break;
            }

            count[current]--;
            matched++;
        }

        // If the left half can equal target's left half, check the full palindrome.
        if (matched == halfLen) {
            String sameLeft = makePalindrome(prefix, middle, n % 2 == 1);
            if (sameLeft.compareTo(target) > 0) {
                return sameLeft;
            }
        }

        // Increase the rightmost possible position, then minimize the suffix.
        if (pivot == -1) {
            return "";
        }

        count = new int[26];
        for (int i = 0; i < 26; i++) {
            count[i] = original[i] / 2;
        }

        StringBuilder left = new StringBuilder();

        // Keep target's prefix unchanged before pivot.
        for (int i = 0; i < pivot; i++) {
            char ch = prefix.charAt(i);
            left.append(ch);
            count[ch - 'a']--;
        }

        // Put the smallest available character greater than target[pivot].
        int current = prefix.charAt(pivot) - 'a';
        for (int ch = current + 1; ch < 26; ch++) {
            if (count[ch] > 0) {
                left.append((char) ('a' + ch));
                count[ch]--;
                break;
            }
        }

        // Minimize all remaining positions.
        for (int ch = 0; ch < 26; ch++) {
            while (count[ch]-- > 0) {
                left.append((char) ('a' + ch));
            }
        }

        return makePalindrome(left.toString(), middle, n % 2 == 1);
    }

    private String makePalindrome(String left, char middle, boolean hasMiddle) {
        StringBuilder result = new StringBuilder(left);

        if (hasMiddle) {
            result.append(middle);
        }

        for (int i = left.length() - 1; i >= 0; i--) {
            result.append(left.charAt(i));
        }

        return result.toString();
    }
}
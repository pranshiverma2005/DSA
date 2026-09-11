class Solution {
    public boolean uniqueOccurrences(int[] arr) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequency of each number
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Store frequencies
        HashSet<Integer> set = new HashSet<>();

        for (int frequency : map.values()) {

            if (set.contains(frequency)) {
                return false;
            }

            set.add(frequency);
        }

        return true;
    }
}

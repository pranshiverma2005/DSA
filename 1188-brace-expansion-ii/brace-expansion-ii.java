class Solution {

    int i = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);
        return new ArrayList<>(result);
    }

    private Set<String> parse(String s) {

        Set<String> result = new TreeSet<>();
        Set<String> current = new TreeSet<>();
        current.add("");

        while (i < s.length() && s.charAt(i) != '}') {

            char ch = s.charAt(i);

            if (ch == ',') {
                // Union
                result.addAll(current);
                current.clear();
                current.add("");
                i++;
            } 
            else {
                // Parse next part
                Set<String> next;

                if (ch == '{') {
                    i++; // skip '{'
                    next = parse(s);
                    i++; // skip '}'
                } 
                else {
                    next = new TreeSet<>();
                    next.add(String.valueOf(ch));
                    i++;
                }

                // Concatenation
                Set<String> combined = new TreeSet<>();

                for (String a : current) {
                    for (String b : next) {
                        combined.add(a + b);
                    }
                }

                current = combined;
            }
        }

        // Add remaining concatenated part
        result.addAll(current);

        return result;
    }
}
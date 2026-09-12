import java.util.*;

class Solution {
    public String decodeString(String s) {

        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        StringBuilder current = new StringBuilder();
        int number = 0;

        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {

                number = number * 10 + (ch - '0');

            } 
            else if (ch == '[') {

                // Save repeat count
                countStack.push(number);

                // Save string before '['
                stringStack.push(current.toString());

                // Start a new string
                current = new StringBuilder();

                number = 0;

            } 
            else if (ch == ']') {

                int k = countStack.pop();
                String previous = stringStack.pop();

                StringBuilder temp = new StringBuilder(previous);

                for (int i = 0; i < k; i++) {
                    temp.append(current);
                }

                current = temp;

            } 
            else {

                current.append(ch);
            }
        }

        return current.toString();
    }
}
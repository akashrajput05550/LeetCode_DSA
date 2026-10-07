import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0;
        int rightRem = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int leftCount, int rightCount,
                           int leftRem, int rightRem, StringBuilder current, Set<String> result) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                result.add(current.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = current.length();

        if (c == '(' && leftRem > 0) {
            backtrack(s, index + 1, leftCount, rightCount, leftRem - 1, rightRem, current, result);
        } else if (c == ')' && rightRem > 0) {
            backtrack(s, index + 1, leftCount, rightCount, leftRem, rightRem - 1, current, result);
        }

        current.append(c);

        if (c != '(' && c != ')') {
            backtrack(s, index + 1, leftCount, rightCount, leftRem, rightRem, current, result);
        } else if (c == '(') {
            backtrack(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, current, result);
        } else if (rightCount < leftCount) {
            backtrack(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, current, result);
        }

        current.setLength(len);
    }
}
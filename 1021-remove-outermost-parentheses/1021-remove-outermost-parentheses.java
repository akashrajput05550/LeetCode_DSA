class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depths = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                if (depths > 0) {
                    result.append(c);
                }
                depths++;
            } else {
                depths--;
                if (depths > 0) {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }
}
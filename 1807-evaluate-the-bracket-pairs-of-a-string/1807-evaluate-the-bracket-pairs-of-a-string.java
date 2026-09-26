class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> entry : knowledge) {
            map.put(entry.get(0), entry.get(1));
        }

        StringBuilder res = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean insideBracket = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                insideBracket = true;
                key.setLength(0);
            } else if (c == ')') {
                insideBracket = false;
                res.append(map.getOrDefault(key.toString(), "?"));
            } else {
                if (insideBracket) {
                    key.append(c);
                } else {
                    res.append(c);
                }
            }
        }

        return res.toString();
    }
}
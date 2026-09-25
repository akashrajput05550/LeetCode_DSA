class Solution {
    int i = 0;

    public List<String> braceExpansionII(String expression) {
        i = 0;
        Set<String> set = parseExpr(expression);
        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    private Set<String> parseExpr(String s) {
        Set<String> res = new HashSet<>();
        while (i < s.length() && s.charAt(i) != '}') {
            res.addAll(parseTerm(s));
            if (i < s.length() && s.charAt(i) == ',') {
                i++;
            }
        }
        return res;
    }

    private Set<String> parseTerm(String s) {
        Set<String> res = new HashSet<>();
        res.add("");

        while (i < s.length() && s.charAt(i) != '}' && s.charAt(i) != ',') {
            Set<String> next;
            if (s.charAt(i) == '{') {
                i++;
                next = parseExpr(s);
                i++;
            } else {
                int start = i;
                while (i < s.length() && Character.isLowerCase(s.charAt(i))) {
                    i++;
                }
                next = Collections.singleton(s.substring(start, i));
            }
            res = multiply(res, next);
        }
        return res;
    }

    private Set<String> multiply(Set<String> set1, Set<String> set2) {
        Set<String> res = new HashSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                res.add(s1 + s2);
            }
        }
        return res;
    }
}
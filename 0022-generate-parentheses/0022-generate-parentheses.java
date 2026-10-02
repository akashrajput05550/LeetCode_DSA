import java.util.*;
class Solution {
    public List<String> generateParenthesis(int n) {
         List<String> res = new ArrayList<>();
         backtracks(res, "", 0, 0, n);
           return res;
    }

    void backtracks(List<String> res, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            res.add(cur);
            return;
        }
        if (open < max)
            backtracks(res, cur + "(", open + 1, close, max);
        if (close < open)
            backtracks(res, cur + ")", open, close + 1, max);
    }
}

class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum < 1 || sum > 9 * num) {
            return "";
        }

        int count9 = sum / 9;
        int rem = sum % 9;

        char[] res = new char[num];
        int idx = 0;

        if (count9 == num) {
            for (int i = 0; i < num; i++) {
                res[i] = '9';
            }
            return new String(res);
        }

        for (int i = 0; i < count9; i++) {
            res[idx++] = '9';
        }

        if (rem > 0) {
            res[idx++] = (char) ('0' + rem);
        }

        while (idx < num) {
            res[idx++] = '0';
        }

        return new String(res);
    }
}
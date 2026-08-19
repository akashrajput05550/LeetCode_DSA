import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMask = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                rowMask.put(row, rowMask.getOrDefault(row, 0) | (1 << (col - 1)));
            }
        }

        int maxFamilies = 0;

        int leftMask = 0b000011110;    
        int rightMask = 0b111100000;   
        int middleMask = 0b001111000; 

        for (int mask : rowMask.values()) {
            boolean leftAvailable = (mask & leftMask) == 0;
            boolean rightAvailable = (mask & rightMask) == 0;
            boolean middleAvailable = (mask & middleMask) == 0;

            if (leftAvailable && rightAvailable) {
                maxFamilies += 2;
            } else if (leftAvailable || rightAvailable || middleAvailable) {
                maxFamilies += 1;
            }
        }

        maxFamilies += (n - rowMask.size()) * 2;

        return maxFamilies;
    }
}
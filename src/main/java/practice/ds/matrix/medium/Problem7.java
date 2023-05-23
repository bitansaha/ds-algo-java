package practice.ds.matrix.medium;

// Count zeros in a row wise and column wise sorted matrix
// https://www.geeksforgeeks.org/count-zeros-in-a-row-wise-and-column-wise-sorted-matrix/

public class Problem7 {

    // Time-Complexity :: O(n)
    // Space-Complexity :: O(1)
    public static int countZeros(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        int count = 0;
        int lastColIndex = cols;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = lastColIndex; colIndex >= 0; colIndex--) {
                if (matrix[rowIndex][colIndex] == 0) {
                    count += colIndex + 1;
                    lastColIndex = colIndex;
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 0, 0, 0, 0, 1 },
                { 0, 0, 0, 1, 1 },
                { 0, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 }
        };

        System.out.println(countZeros(matrix));
    }
}

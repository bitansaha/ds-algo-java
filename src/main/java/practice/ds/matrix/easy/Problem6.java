package practice.ds.matrix.easy;

// Find maximum element of each row in a matrix
// https://www.geeksforgeeks.org/find-maximum-element-row-matrix/

public class Problem6 {

    // Time Complexity :: O(n^2)
    // Space Complexity :: O(1)
    public static void findMaxElementEachRow(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            int max = Integer.MIN_VALUE;

            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                max = Math.max(max, matrix[rowIndex][colIndex]);
            }

            System.out.println("Row " + rowIndex + " MAX value: " + max);
        }
    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {1, 2, 3},
                {1, 4, 9},
                {76, 34, 21}
        };
        System.out.println(" =========== Matrix ONE ============ ");
        findMaxElementEachRow(matrixOne);

        int[][] matrixTwo = {
                {1, 2, 3, 21},
                {12, 1, 65, 9},
                {1, 56, 34, 2}
        };
        System.out.println(" =========== Matrix TWO ============ ");
        findMaxElementEachRow(matrixTwo);
    }
}

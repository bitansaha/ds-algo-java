package practice.ds.matrix.easy;

// Program to check idempotent matrix
// https://www.geeksforgeeks.org/program-check-idempotent-matrix/
public class Problem10 {

    // Time Complexity :: O(n^3)
    // Space Complexity :: O(1)
    public static boolean isIdempotent(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                int matrixIndexValue = matrix[rowIndex][colIndex];
                int matrixMultipliedValue = 0;

                for (int mmColIndex = 0, mmRowIndex = 0; mmColIndex <= cols && mmRowIndex <= rows; mmColIndex++, mmRowIndex++) {
                    matrixMultipliedValue += matrix[rowIndex][mmColIndex] * matrix[mmRowIndex][colIndex];
                }

                if (matrixIndexValue != matrixMultipliedValue)
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {3, -6},
                {1, -2}
        };
        System.out.println(" ======= Matrix ONE is idempotent: " + isIdempotent(matrixOne));

        int[][] matrixTwo = {
                {2, -2, -4},
                {-1, 3, 4},
                {1, -2, -3}
        };
        System.out.println(" ======= Matrix TWO is idempotent: " + isIdempotent(matrixTwo));
    }
}

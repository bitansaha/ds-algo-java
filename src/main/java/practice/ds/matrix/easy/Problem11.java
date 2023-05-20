package practice.ds.matrix.easy;

// Program to check diagonal matrix and scalar matrix
// https://www.geeksforgeeks.org/program-check-diagonal-matrix-scalar-matrix/
public class Problem11 {

    // Time Complexity :: O(n^2)
    // Space Complexity :: O(1)
    public static boolean isDiagonal(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                if ((rowIndex != colIndex && matrix[rowIndex][colIndex] != 0) || (rowIndex == colIndex && matrix[rowIndex][colIndex] == 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {4, 0, 0, 0},
                {0, 5, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 1}
        };
        System.out.println(" ===== Matrix One is diagonal: " + isDiagonal(matrixOne));

        int[][] matrixTwo = {
                {6, 10, 12, 0},
                {0, 5, 0, 0},
                {0, 0, 9, 0},
                {0, 0, 0, 1}
        };
        System.out.println(" ===== Matrix Two is diagonal: " + isDiagonal(matrixTwo));
    }
}

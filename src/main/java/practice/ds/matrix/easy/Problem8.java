package practice.ds.matrix.easy;

// Swap major and minor diagonals of a square matrix
// https://www.geeksforgeeks.org/swap-major-minor-diagonals-square-matrix/
public class Problem8 {

    // Time Complexity :: O(n)
    // Space Complexity :: O(1)
    public static int[][] swapDiagonals(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0, majorColIndex = 0, minorColIndex = cols; rowIndex <= rows; rowIndex++, majorColIndex++, minorColIndex--) {
            int temp = matrix[rowIndex][majorColIndex];
            matrix[rowIndex][majorColIndex] = matrix[rowIndex][minorColIndex];
            matrix[rowIndex][minorColIndex] = temp;
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                System.out.print(matrix[rowIndex][colIndex] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        };

        System.out.println(" =========== Matrix ONE =========== ");
        printMatrix(matrixOne);
        System.out.println(" =========== Swap Diagonal Matrix ONE =========== ");
        printMatrix(swapDiagonals(matrixOne));
    }
}

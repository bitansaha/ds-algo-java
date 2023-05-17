package practice.ds.matrix.easy;

public class Problem2 {

    // Program for scalar multiplication of a matrix
    // https://www.geeksforgeeks.org/program-for-scalar-multiplication-of-a-matrix/

    // Time-Complexity :: O(n*m)
    // Space-Complexity :: O(1)
    public static int[][] scalarMultiplication(int[][] matrix, int scalarValue) {
        int rows = matrix.length - 1;
        int cols = matrix[0].length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                matrix[rowIndex][colIndex] = matrix[rowIndex][colIndex] * scalarValue;
            }
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix[0].length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                System.out.print(matrix[rowIndex][colIndex] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {2, 3},
                {5, 4}
        };
        int scalarValueOne = 5;

        System.out.println(" ========== Matrix ONE ========== ");
        printMatrix(matrixOne);
        System.out.println("Scalar Value: " + scalarValueOne);
        int[][] scalarMultipliedMatrixOne = scalarMultiplication(matrixOne, scalarValueOne);
        printMatrix(scalarMultipliedMatrixOne);

        int[][] matrixTwo = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int scalarValueTwo = 4;

        System.out.println(" ========== Matrix TWO ========== ");
        printMatrix(matrixTwo);
        System.out.println("Scalar Value: " + scalarValueTwo);
        int[][] scalarMultipliedMatrixTwo = scalarMultiplication(matrixTwo, scalarValueTwo);
        printMatrix(scalarMultipliedMatrixTwo);

    }
}

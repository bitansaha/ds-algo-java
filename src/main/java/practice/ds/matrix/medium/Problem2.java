package practice.ds.matrix.medium;
// Program to multiply two matrices
// https://www.geeksforgeeks.org/c-program-multiply-two-matrices/
public class Problem2 {

    // Time-Complexity :: O(n^3)
    // Space-Complexity :: O(1)
    public static int[][] multiplyMatrix(int[][] matrixOne, int[][] matrixTwo) {
        int matrixOneRows = matrixOne.length - 1;
        int matrixOneCols = matrixOne.length >= 1 ? matrixOne[0].length - 1 : 0;

        int matrixTwoRows = matrixTwo.length - 1;
        int matrixTwoCols = matrixTwo.length >= 1 ? matrixOne[0].length - 1 : 0;

        if (matrixOneCols != matrixTwoRows)
            throw new RuntimeException("First Matrix Row Count should be Equal to Second Matrix Row Count");

        int[][] resultantMatrix = new int[matrixOneRows + 1][matrixTwoCols + 1];

        for (int rowIndex = 0; rowIndex <= matrixOneRows; rowIndex++) {
            for (int colIndex = 0; colIndex <= matrixTwoCols; colIndex++) {
                int value = 0;
                for (int matrixOneColIndex = 0, matrixTwoRowIndex = 0; matrixOneColIndex <= matrixOneCols && matrixTwoRowIndex <= matrixTwoRows; matrixOneColIndex++, matrixTwoRowIndex++) {
                    value += matrixOne[rowIndex][matrixOneColIndex] * matrixTwo[matrixTwoRowIndex][colIndex];
                }
                resultantMatrix[rowIndex][colIndex] = value;
            }
        }

        return resultantMatrix;
    }

    public static void printMatrix (int[][] matrix) {
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
                { 1, 1 },
                { 2, 2 }
        };

        int[][] matrixTwo = {
                { 1, 1 },
                { 2, 2 }
        };

        System.out.println(" ========== Matrix ONE ========== ");
        printMatrix(matrixOne);

        System.out.println(" ========== Matrix TWO ========== ");
        printMatrix(matrixTwo);

        System.out.println(" ========== Resultant Matrix ========== ");
        printMatrix(multiplyMatrix(matrixOne, matrixTwo));
    }
}

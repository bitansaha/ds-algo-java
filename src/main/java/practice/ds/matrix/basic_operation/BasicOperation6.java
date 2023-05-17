package practice.ds.matrix.basic_operation;

public class BasicOperation6 {

    // Program to find transpose of a matrix
    // https://www.geeksforgeeks.org/program-to-find-transpose-of-a-matrix/

    public static int[][] transposeMatrix(int[][] matrix) {
        int rowLength = matrix.length;
        int colLength = matrix.length >= 1 ? matrix[0].length : 0;

        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        if (rowLength == colLength) {
            // Square Matrix
            for (int masterRowIndex = 0, masterColIndex = 0; masterRowIndex <= rows && masterColIndex <= cols; masterRowIndex++, masterColIndex++) {
                for (int rowIndex = masterRowIndex, colIndex = masterColIndex; rowIndex <= rows && colIndex <= cols; rowIndex++, colIndex++) {
                    int temp = matrix[rowIndex][masterColIndex];
                    matrix[rowIndex][masterColIndex] = matrix[masterRowIndex][colIndex];
                    matrix[masterRowIndex][colIndex] = temp;
                }
            }

            return matrix;
        } else {
            int[][] transposeMatrix = new int[colLength][rowLength];

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    transposeMatrix[colIndex][rowIndex] = matrix[rowIndex][colIndex];
                }
            }

            return transposeMatrix;
        }
    }

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        for (int currentRow = 0; currentRow <= rows; currentRow++) {
            System.out.print("\n");
            for (int currentCol = 0; currentCol <= cols; currentCol++) {
                System.out.print(matrix[currentRow][currentCol] + ", ");
            }
        }
    }

    public static void main(String[] args) {

        int[][] matrixOne = {
            { 1, 1, 1, 1 },
            { 2, 2, 2, 2 },
            { 3, 3, 3, 3 },
            { 4, 4, 4, 4 }
        };

        System.out.println(" ======= MatrixONE: ");
        printMatrix(matrixOne);
        int[][] transposeMatrixOne = transposeMatrix(matrixOne);
        System.out.println("\n ======= Transpose-MatrixONE: ");
        printMatrix(transposeMatrixOne);
    }
}

package practice.ds.matrix.hard;
// A Boolean Matrix Question
// https://www.geeksforgeeks.org/a-boolean-matrix-question/
public class Problem2 {

    // Time-Complexity :: O(m*n)
    // Space-Complexity :: O(1)
    public static int[][] markMatrix(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                if (matrix[rowIndex][colIndex] == 1) {
                    if (matrix[0][colIndex] == 0) {
                        matrix[0][colIndex] = -1;
                    }

                    if (matrix[rowIndex][0] == 0) {
                        matrix[rowIndex][0] = -1;
                    }
                }
            }
        }

        boolean isFirstColumnActive = false;
        boolean isFirstRowActive = false;

        for (int rowIndex = 1; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 1; colIndex <= cols; colIndex++) {
                if (matrix[0][colIndex] != 0 || matrix[rowIndex][0] != 0) {
                    matrix[rowIndex][colIndex] = 1;
                }

                if (matrix[0][colIndex] == 1) {
                    isFirstRowActive = true;
                }

                if (matrix[rowIndex][0] == 1) {
                    isFirstColumnActive = true;
                }
            }
        }

        if (matrix[0][0] == 1) {
            isFirstColumnActive = true;
            isFirstRowActive = true;
        }

        if (isFirstColumnActive) {
            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                matrix[rowIndex][0] = 1;
            }
        } else {
            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                if (matrix[rowIndex][0] == -1) {
                    matrix[rowIndex][0] = 1;
                }
            }
        }

        if (isFirstRowActive) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                matrix[0][colIndex] = 1;
            }
        } else {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                if (matrix[0][colIndex] == -1) {
                    matrix[0][colIndex] = 1;
                }
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
                {1, 0},
                {0, 0}
        };
        System.out.println(" ========= Matrix ONE ========== ");
        printMatrix(markMatrix(matrixOne));
        System.out.println("\n");

        int[][] matrixTwo = {
                {0, 0, 0},
                {0, 0, 1}
        };
        System.out.println(" ========= Matrix TWO ========== ");
        printMatrix(markMatrix(matrixTwo));
        System.out.println("\n");

        int[][] matrixThree = {
                {1, 0, 0, 1},
                {0, 0, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println(" ========= Matrix THREE ========== ");
        printMatrix(markMatrix(matrixThree));
        System.out.println("\n");

    }
}

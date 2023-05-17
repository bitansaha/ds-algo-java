package practice.ds.matrix.easy;

public class Problem1 {

    // Print matrix in zig-zag fashion
    // https://www.geeksforgeeks.org/print-matrix-in-zig-zag-fashion/

    // Time-Complexity :: O(n*m)
    // Space-Complexity :: O(1)
    public static void printZigZagMatrix(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        int rowIndex = 0;
        int colIndex = 0;

        System.out.print(matrix[rowIndex][colIndex] + " ");

        masterLoop:
        while (true) {
            if (colIndex + 1 <= cols) {
                System.out.print(matrix[rowIndex][++colIndex] + " ");
            } else if (rowIndex + 1 <= rows) {
                System.out.print(matrix[++rowIndex][colIndex] + " ");
            } else {
                break masterLoop;
            }

            while (rowIndex + 1 <= rows && colIndex - 1 >= 0) {
                System.out.print(matrix[++rowIndex][--colIndex] + " ");
            }

            if (rowIndex + 1 <= rows) {
                System.out.print(matrix[++rowIndex][colIndex] + " ");
            } else if (colIndex + 1 <= cols) {
                System.out.print(matrix[rowIndex][++colIndex] + " ");
            } else {
                break masterLoop;
            }

            while (rowIndex - 1 >= 0 && colIndex + 1 <= cols) {
                System.out.print(matrix[--rowIndex][++colIndex] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        printZigZagMatrix(matrixOne);
    }
}

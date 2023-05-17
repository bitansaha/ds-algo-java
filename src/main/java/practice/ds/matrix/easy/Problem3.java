package practice.ds.matrix.easy;

public class Problem3 {
    // Print a given matrix in spiral form
    // https://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/

    // Time-Complexity :: O(n*m)
    // Space-Complexity :: O(1)
    public static void printSpiral(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        int minRow = 0;
        int maxRow = rows;

        int minCol = 0;
        int maxCol = cols;

        int rowIndex = 0;
        int colIndex = 0;

        System.out.print(matrix[rowIndex][colIndex] + " ");

        while (minRow <= maxRow || minCol <= maxCol) {
            while (colIndex + 1 <= maxCol) {
                System.out.print(matrix[rowIndex][++colIndex] + " ");
            }
            minRow++;

            while (rowIndex + 1 <= maxRow) {
                System.out.print(matrix[++rowIndex][colIndex] + " ");
            }
            maxCol--;

            while (colIndex - 1 >= minCol) {
                System.out.print(matrix[rowIndex][--colIndex] + " ");
            }
            maxRow--;

            while (rowIndex - 1 >= minRow) {
                System.out.print(matrix[--rowIndex][colIndex] + " ");
            }
            minCol++;
        }
    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {1,    2,   3,   4},
                {5,    6,   7,   8},
                {9,   10,  11,  12},
                {13,  14,  15,  16 }
        };
        printSpiral(matrixOne);
    }
}

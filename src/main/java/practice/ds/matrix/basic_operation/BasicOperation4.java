package practice.ds.matrix.basic_operation;

import java.util.Arrays;

public class BasicOperation4 {

    // Sort the given matrix
    // https://www.geeksforgeeks.org/sort-given-matrix/

    public static int[][] sortMatrix (int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        int rowLength = matrix.length;
        int colLength = matrix.length >= 1 ? matrix[0].length : 0;

        int[] tempArray = new int[rowLength * colLength];

        for (int rowIndex = 0, tempArrayIndex = -1; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                tempArray[++tempArrayIndex] = matrix[rowIndex][colIndex];
            }
        }

        Arrays.sort(tempArray);

        for (int rowIndex = 0, tempArrayIndex = -1; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                matrix[rowIndex][colIndex] = tempArray[++tempArrayIndex];
            }
        }

        return matrix;
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
        int[][] matrix = {
                {5, 4, 7},
                {1, 3, 8},
                {2, 9, 6}
        };
        System.out.print("Input Matrix: ");
        printMatrix(matrix);
        int[][] sortedMatrix = sortMatrix(matrix);
        System.out.print("\n\nSorted Matrix: ");
        printMatrix(sortedMatrix);
    }
}

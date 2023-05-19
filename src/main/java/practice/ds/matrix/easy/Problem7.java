package practice.ds.matrix.easy;

// Shift matrix elements row-wise by k
// https://www.geeksforgeeks.org/shift-matrix-elements-k/#

public class Problem7 {

    // Time Complexity :: O(n^2)
    // Space Complexity :: O(1)

    public static void reverseArray(int[] array, int startIndex, int endIndex) {
        for (; startIndex < endIndex; startIndex++, endIndex--) {
            int temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
        }
    }

    public static void rotateArray(int[] array, int rotationValue) {
        reverseArray(array, 0, rotationValue - 1);
        reverseArray(array, rotationValue, array.length - 1);
        reverseArray(array, 0, array.length - 1);
    }

    public static int[][] shiftMatrixRow(int[][] matrix, int shiftValue) {
        int rows = matrix.length - 1;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            rotateArray(matrix[rowIndex], shiftValue);
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
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(" ========== Matrix ONE =========== ");
        printMatrix(matrixOne);
        System.out.println(" ========== Matrix ONE Shifted =========== ");
        printMatrix(shiftMatrixRow(matrixOne, 2));

        int [][] matrixTwo = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println(" ========== Matrix TWO =========== ");
        printMatrix(matrixTwo);
        System.out.println(" ========== Matrix TWO Shifted =========== ");
        printMatrix(shiftMatrixRow(matrixTwo, 2));

    }
}

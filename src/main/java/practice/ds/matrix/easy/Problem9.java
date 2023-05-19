package practice.ds.matrix.easy;

// Sum of middle row and column in Matrix
// https://www.geeksforgeeks.org/sum-middle-row-column-matrix/
public class Problem9 {

    // Time Complexity :: O(n)
    // Space Complexity :: O(1)
    public static void computeMiddleRowColumn(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        int rowMid = matrix.length / 2;
        int colMid = matrix.length >= 1 ? matrix[0].length / 2 : 0;

        int midRowSum = 0;
        int midColSum = 0;

        for (int colIndex = 0; colIndex <= cols; colIndex++) {
            midRowSum += matrix[rowMid][colIndex];
        }

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            midColSum += matrix[rowIndex][colMid];
        }

        System.out.println("Mid Row Sum: " + midRowSum);
        System.out.println("Mid Col Sum: " + midColSum);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {2, 5, 7},
                {3, 7, 2},
                {5, 6, 9}
        };

        computeMiddleRowColumn(matrix);
    }
}

package practice.ds.matrix.medium;
// Check if all rows of a matrix are circular rotations of each other
// https://www.geeksforgeeks.org/check-rows-matrix-circular-rotations/
public class Problem4 {

    // Time-Complexity :: O(n^2)
    // Space-Complexity :: O(1)
    public static boolean isAllRowsCircular(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rows - 1; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                if (matrix[rowIndex][colIndex] != matrix[rowIndex + 1][(colIndex + 1) % (cols + 1)])
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {4, 1, 2, 3},
                {3, 4, 1, 2},
                {2, 3, 4, 1}
        };
        System.out.println(" ======= Matrix One all rows rotated: " + isAllRowsCircular(matrix));
    }
}

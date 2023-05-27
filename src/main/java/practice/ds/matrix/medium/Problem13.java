package practice.ds.matrix.medium;
// Minimum flip required to make Binary Matrix symmetric
// https://www.geeksforgeeks.org/minimum-flip-required-make-binary-matrix-symmetric/
public class Problem13 {

    // Time-Complexity :: O(n^2)
    // Space-Complexity :: O(1)
    public static void countFlipForSymmetric(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        int flipCount = 0;

        for (int rowIndex = 0, colIndex = 0; rowIndex <= rows && colIndex <= cols; rowIndex++, colIndex++) {
            for (int secRowIndex = rowIndex + 1, secColIndex = colIndex + 1; secRowIndex <= rows && secColIndex <= cols; secRowIndex++, secColIndex++) {
                flipCount += matrix[secRowIndex][colIndex] != matrix[rowIndex][secColIndex] ? 1 : 0;
            }
        }

        System.out.println(" ====== Required flips: " + flipCount + " ====== ");
    }
    public static void main(String[] args) {
        int[][] matrixOne = {
                { 0, 0, 1 },
                { 1, 1, 1 },
                { 1, 0, 0 }
        };

        System.out.println(" ================== Matrix ONE ================== ");
        countFlipForSymmetric(matrixOne);

        int[][] matrixTwo = {
                { 1, 1, 1, 1, 0 },
                { 0, 1, 0, 1, 1 },
                { 1, 0, 0, 0, 1 },
                { 0, 1, 0, 1, 0 },
                { 0, 1, 0, 0, 1 }
        };

        System.out.println(" ================== Matrix TWO ================== ");
        countFlipForSymmetric(matrixTwo);
    }
}

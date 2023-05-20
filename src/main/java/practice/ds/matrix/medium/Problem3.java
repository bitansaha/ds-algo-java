package practice.ds.matrix.medium;
// Rotate a matrix by 90 degree without using any extra space
// https://www.geeksforgeeks.org/rotate-matrix-90-degree-without-using-extra-space-set-2/
public class Problem3 {

    // Time-Complexity :: O(n^2)
    // Space-Complexity :: O(1)
    public static int[][] rotateMatrixBy90(int[][] matrix) {
        int rowStart = 0;
        int colStart = 0;

        int rowEnd = matrix.length - 1;
        int colEnd = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        while (rowStart < rowEnd) {
            for (
                    int leftRowIndex = rowStart, bottomColIndex = colStart, rightRowIndex = rowEnd, topColIndex = colEnd;
                    leftRowIndex < rowEnd;
                    leftRowIndex++, bottomColIndex++, rightRowIndex--, topColIndex--
            ) {
                int tempOne = matrix[rowEnd][bottomColIndex];
                matrix[rowEnd][bottomColIndex] = matrix[leftRowIndex][colStart];
                int tempTwo = matrix[rightRowIndex][colEnd];
                matrix[rightRowIndex][colEnd] = tempOne;
                tempOne = tempTwo;
                tempTwo = matrix[rowStart][topColIndex];
                matrix[rowStart][topColIndex] = tempOne;
                matrix[leftRowIndex][colStart] = tempTwo;
            }
            rowStart++;
            colStart++;
            rowEnd--;
            colEnd--;
        }

        return matrix;
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
        int[][] matrix = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 }
        };

        printMatrix(rotateMatrixBy90(matrix));
    }
}

package practice.ds.matrix.medium;
// Magic Square | ODD Order
// https://www.geeksforgeeks.org/magic-square/
public class Problem14 {

    // Time-Complexity :: O(n^2)
    // Space-Complexity :: O(n^2)
    public static void printMatrix (int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                System.out.print(matrix[rowIndex][colIndex] + " ");
            }
            System.out.println("");
        }
    }

    public static int[][] generateMagicSquare(int squareLength) {
        int[][] magicMatrix = new int[squareLength][squareLength];

        int count = 1;
        int nextRowIndex = squareLength / 2;
        int nextColIndex = squareLength - 1;

        while (count <= squareLength * squareLength) {
            if (nextRowIndex == -1 && nextColIndex == squareLength) {
                nextRowIndex = 0;
                nextColIndex = squareLength - 2;
            } else if (nextRowIndex == -1 && nextColIndex < squareLength) {
                nextRowIndex = squareLength - 1;
            } else if (nextColIndex == squareLength && nextRowIndex > -1) {
                nextColIndex = 0;
            } else if (magicMatrix[nextRowIndex][nextColIndex] > 0) {
                nextRowIndex += 1;
                nextColIndex -= 2;
            } else {
                magicMatrix[nextRowIndex][nextColIndex] = count;
                nextRowIndex -= 1;
                nextColIndex += 1;
                count++;
            }
        }

        return magicMatrix;
    }

    public static void main(String[] args) {
        int[][] magicMatrixThree = generateMagicSquare(3);
        System.out.println(" ========== Magic Matrix 3 =========== ");
        printMatrix(magicMatrixThree);

        int[][] magicMatrixFive = generateMagicSquare(5);
        System.out.println(" ========== Magic Matrix 5 =========== ");
        printMatrix(magicMatrixFive);
    }
}

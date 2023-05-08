package practice.ds.matrix.basic_operation;

public class BasicOperation2 {

    /**
     * Traverse a given Matrix using Recursion
     * https://www.geeksforgeeks.org/traverse-a-given-matrix-using-recursion/
     *
     */

    public static void recursionBasedTraversal(int[][] matrix, int rowIndex, int colIndex) {
        int rowSize = matrix.length - 1;
        int colSize = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        System.out.print(matrix[rowIndex][colIndex] + "\t");

        if (colIndex == colSize) {
            System.out.println("\n");
            if(++rowIndex <= rowSize) {
                recursionBasedTraversal(matrix, rowIndex, 0);
            }
        } else {
            recursionBasedTraversal(matrix, rowIndex, ++colIndex);
        }

    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println(" ============ Matrix One ============= ");
        recursionBasedTraversal(matrixOne, 0, 0);

        int[][] matrixTwo = {
                {11, 12, 13},
                {14, 15, 16},
                {17, 18, 19}
        };

        System.out.println(" ============ Matrix Two ============= ");
        recursionBasedTraversal(matrixTwo, 0, 0);
    }
}

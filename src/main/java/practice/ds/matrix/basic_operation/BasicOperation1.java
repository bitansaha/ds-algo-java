package practice.ds.matrix.basic_operation;

public class BasicOperation1 {

    /**
     * Row-wise vs column-wise traversal of matrix
     * https://www.geeksforgeeks.org/row-wise-vs-column-wise-traversal-matrix/
     *
     */


    public static void rowWiseTraversal(int[][] matrix) {
        int rowLength = matrix.length - 1;
        int colLength = matrix.length >= 1? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rowLength; rowIndex++) {
            for (int colIndex = 0; colIndex <= colLength; colIndex++) {
                System.out.print(matrix[rowIndex][colIndex] + "\t");
            }
            System.out.println("\n");
        }

    }

    public static void columnWiseTraversal(int[][] matrix) {
        int rowLength = matrix.length - 1;
        int colLength = matrix.length >= 1? matrix[0].length - 1 : 0;

        for (int colIndex = 0; colIndex <= colLength; colIndex++) {
            for (int rowIndex = 0; rowIndex <= rowLength; rowIndex++) {
                System.out.print(matrix[rowIndex][colIndex] + "\t");
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println(" ============== Row wise Matrix Traversal =============== ");
        rowWiseTraversal(matrix);
        System.out.println("\n ============== Col wise Matrix Traversal =============== ");
        columnWiseTraversal(matrix);
    }
}

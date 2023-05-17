package practice.ds.matrix.basic_operation;

public class BasicOperation5 {

    // Search element in a sorted matrix
    // https://www.geeksforgeeks.org/search-element-sorted-matrix/

    public static int[] searchMatrix(int[][] matrix, int searchTerm) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        int topSearchRow = 0;
        int bottomSearchRow = rows;
        int verticalSearchIndex = -1;

        while (topSearchRow <= bottomSearchRow) {
            verticalSearchIndex = (bottomSearchRow - topSearchRow + 1)/2 + topSearchRow;

            if (matrix[verticalSearchIndex][0] == searchTerm) {
                return new int[] {verticalSearchIndex, 0};
            } else if (matrix[verticalSearchIndex][0] < searchTerm) {
                topSearchRow = verticalSearchIndex + 1;
            } else {
                bottomSearchRow = verticalSearchIndex - 1;
            }
        }

        int leftSearchColumn = 0;
        int rightSearchColumn = cols;
        int horizontalSearchIndex = -1;

        while (leftSearchColumn <= rightSearchColumn) {
            horizontalSearchIndex = (rightSearchColumn - leftSearchColumn + 1)/2 + leftSearchColumn;

            if (matrix[verticalSearchIndex][horizontalSearchIndex] == searchTerm) {
                return new int[] {verticalSearchIndex, horizontalSearchIndex};
            } else if (matrix[verticalSearchIndex][horizontalSearchIndex] < searchTerm) {
                leftSearchColumn = horizontalSearchIndex + 1;
            } else {
                rightSearchColumn = horizontalSearchIndex - 1;
            }
        }

        return new int[] {-1, -1};


    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {1, 5, 9},
                {14, 20, 21},
                {30, 34, 43}
        };
        int matrixOneSearchTerm = 14;
        int[] matrixOneSearchIndex = searchMatrix(matrixOne, matrixOneSearchTerm);
        System.out.println(" ========= Searching " + matrixOneSearchTerm + " in MatrixONE -- [" + matrixOneSearchIndex[0] + ", " + matrixOneSearchIndex[1] + "] ========= ");

        int[][] matrixTwo = {
                {1, 5, 9, 11},
                {14, 20, 21, 26},
                {30, 34, 43, 50}
        };
        int matrixTwoSearchTerm = 42;
        int[] matrixTwoSearchIndex = searchMatrix(matrixTwo, matrixTwoSearchTerm);
        System.out.println(" ========= Searching " + matrixTwoSearchTerm + " in MatrixTWO -- [" + matrixTwoSearchIndex[0] + ", " + matrixTwoSearchIndex[1] + "] ========= ");
    }
}

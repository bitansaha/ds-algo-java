package practice.ds.matrix.basic_operation;

public class BasicOperation3 {

    /**
     *  Rotate Matrix Elements
     *  https://www.geeksforgeeks.org/rotate-matrix-elements/
     */

    public static class MultiIndexRotation {
        public static void reverseMatrixEdge (
                int rowIndex,
                int colIndex,
                int leftRowIndex,
                int leftColIndex,
                int rightRowIndex,
                int rightColIndex,
                int rowBoundary,
                int colBoundary,
                int [][] matrix
        ) {
            if (leftRowIndex == rightRowIndex && leftColIndex == rightColIndex) return;

            int currentLeftRowIndex = leftRowIndex;
            int currentLeftColIndex = leftColIndex;

            int currentRightRowIndex = rightRowIndex;
            int currentRightColIndex = rightColIndex;

            loop:
            while (true) {

                int temp = matrix[currentLeftRowIndex][currentLeftColIndex];
                matrix[currentLeftRowIndex][currentLeftColIndex] = matrix[currentRightRowIndex][currentRightColIndex];
                matrix[currentRightRowIndex][currentRightColIndex] = temp;

                // Shifting two-dimensional left index to right
                if ( (currentLeftColIndex + 1) <= colBoundary && currentLeftRowIndex == rowIndex) {
                    currentLeftColIndex = currentLeftColIndex + 1;
                } else if ( (currentLeftRowIndex + 1) <= rowBoundary && currentLeftColIndex == colBoundary) {
                    currentLeftRowIndex = currentLeftRowIndex + 1;
                } else if ( (currentLeftColIndex - 1) >= colIndex) {
                    currentLeftColIndex = currentLeftColIndex - 1;
                } else {
                    currentLeftRowIndex = currentLeftRowIndex - 1;
                }

                // Shifting two-dimensional right index to left
                if ( (currentRightRowIndex + 1) <= rowBoundary && currentRightColIndex == colIndex) {
                    currentRightRowIndex = currentRightRowIndex + 1;
                } else if ( (currentRightColIndex + 1) <= colBoundary && currentRightRowIndex == rowBoundary) {
                    currentRightColIndex = currentRightColIndex + 1;
                } else if ( (currentRightRowIndex - 1) >= rowIndex) {
                    currentRightRowIndex = currentRightRowIndex - 1;
                } else {
                    currentRightColIndex = currentRightColIndex - 1;
                }

                // termination logic
                if (currentLeftRowIndex == currentRightRowIndex && currentLeftRowIndex == rowIndex) {
                    if (currentLeftColIndex >= currentRightColIndex) break loop;
                } else if (currentLeftRowIndex == currentRightRowIndex && currentLeftRowIndex == rowBoundary) {
                    if (currentLeftColIndex <= currentRightColIndex) break loop;
                } else if (currentLeftColIndex == currentRightColIndex && currentLeftColIndex == colIndex) {
                    if (currentLeftRowIndex <= currentRightRowIndex) break loop;
                } else if (currentLeftColIndex == currentRightColIndex && currentLeftColIndex == colBoundary) {
                    if (currentLeftRowIndex >= currentRightRowIndex) break loop;
                }

            }
        }

        public static void rotateMatrix (
                int rotationCount,
                int [][] matrix
        ) {
            int colBoundary = matrix.length >= 1 ? matrix[0].length - 1 : 0;
            int rowBoundary = matrix.length - 1;

            /**
             * Going down the diagonal path and establishing the perimeter boundaries of the
             * current matrix space to be rotated
             */
            for (
                    int rowIndex = 0, colIndex = 0;
                    rowIndex < rowBoundary && colIndex < colBoundary;
                    rowIndex++, colIndex++, rowBoundary--, colBoundary--
            ) {
                // Total number of indexes representing the current matrix perimeter
                int totalIndexes = ((colBoundary - colIndex + 1) * 2) + ((rowBoundary - rowIndex - 1) * 2) ;
                int effectiveRotationCount = rotationCount >= totalIndexes ? rotationCount % totalIndexes : rotationCount;

                // Using Left-Reverse, Right-Reverse and Full-Reverse approach to rotate the perimeter of matrix
                // Establishing the indexes of the Left and Right sides
                int leftLeftRowIndex = rowIndex;
                int leftLeftColIndex = colIndex + 1;

                int leftRightRowIndex = leftLeftRowIndex;
                int leftRightColIndex = leftLeftColIndex;

                // Establishing the end of the Left Side
                // TODO: Check if we can establish the end of the left side Mathematically
                for (int count = 0; count < totalIndexes - effectiveRotationCount - 1; count++) {
                    if ( (leftRightColIndex + 1) <= colBoundary && leftRightRowIndex == leftLeftRowIndex) {
                        leftRightColIndex = leftRightColIndex + 1;
                    } else if ( (leftRightRowIndex + 1) <= rowBoundary && leftRightColIndex == colBoundary) {
                        leftRightRowIndex = leftRightRowIndex + 1;
                    } else if ( (leftRightColIndex - 1) >= colIndex) {
                        leftRightColIndex = leftRightColIndex - 1;
                    } else {
                        leftRightRowIndex = leftRightRowIndex - 1;
                    }
                }

                int rightLeftRowIndex = leftRightRowIndex;
                int rightLeftColIndex = leftRightColIndex;

                if ( (rightLeftColIndex + 1) <= colBoundary && leftRightRowIndex == leftLeftRowIndex) {
                    rightLeftColIndex = rightLeftColIndex + 1;
                } else if ( (rightLeftRowIndex + 1) <= rowBoundary && leftRightColIndex == colBoundary) {
                    rightLeftRowIndex = rightLeftRowIndex + 1;
                } else if ( (rightLeftColIndex - 1) >= leftLeftColIndex) {
                    rightLeftColIndex = rightLeftColIndex - 1;
                } else {
                    rightLeftRowIndex = rightLeftRowIndex - 1;
                }

                int rightRightRowIndex = rowIndex;
                int rightRightColIndex = colIndex;

                // Left-Reverse
                reverseMatrixEdge (
                        rowIndex,
                        colIndex,
                        leftLeftRowIndex,
                        leftLeftColIndex,
                        leftRightRowIndex,
                        leftRightColIndex,
                        rowBoundary,
                        colBoundary,
                        matrix
                );

                // Right-Reverse
                reverseMatrixEdge (
                        rowIndex,
                        colIndex,
                        rightLeftRowIndex,
                        rightLeftColIndex,
                        rightRightRowIndex,
                        rightRightColIndex,
                        rowBoundary,
                        colBoundary,
                        matrix
                );

                // Full-Reverse
                reverseMatrixEdge (
                        rowIndex,
                        colIndex,
                        leftLeftRowIndex,
                        leftLeftColIndex,
                        rightRightRowIndex,
                        rightRightColIndex,
                        rowBoundary,
                        colBoundary,
                        matrix
                );
            }
        }
    }

    public static class SingleIndexRotation {
        private static int getNextRow (
                int firstRow,
                int leftCol,
                int lastRow,
                int rightCol,
                int currentRow,
                int currentCol
        ) {
            if (currentRow == firstRow && currentCol < rightCol) {
                return currentRow;
            } else if (currentRow == firstRow && currentCol == rightCol) {
                return currentRow + 1;
            } else if (currentCol == rightCol && currentRow < lastRow) {
                return currentRow + 1;
            } else if (currentCol == rightCol && currentRow == lastRow) {
                return currentRow;
            } else if (currentRow == lastRow && currentCol > leftCol) {
                return currentRow;
            } else if (currentRow == lastRow && currentCol == leftCol) {
                return currentRow - 1;
            } else {
                return currentRow - 1;
            }
        }

        private static int getNextCol (
                int firstRow,
                int leftCol,
                int lastRow,
                int rightCol,
                int currentRow,
                int currentCol
        ) {
            if (currentRow == firstRow && currentCol < rightCol) {
                return currentCol + 1;
            } else if (currentRow == firstRow && currentCol == rightCol) {
                return currentCol;
            } else if (currentCol == rightCol && currentRow < lastRow) {
                return currentCol;
            } else if (currentCol == rightCol && currentRow == lastRow) {
                return currentCol - 1;
            } else if (currentRow == lastRow && currentCol > leftCol) {
                return currentCol - 1;
            } else if (currentRow == lastRow && currentCol == leftCol) {
                return currentCol;
            } else {
                return currentCol;
            }
        }
        public static void rotateMatrix(int[][] matrix) {
            for (
                    int firstRow = 0, leftCol = 0, lastRow = matrix.length - 1, rightCol = matrix.length >= 1 ? matrix[0].length - 1 : 0;
                    firstRow < lastRow && leftCol < rightCol;
                    firstRow++, leftCol++, lastRow--, rightCol--
            ) {
                int currentRow = firstRow;
                int currentCol = leftCol;

                int prev = matrix[currentRow][currentCol];

                do {
                    int nextRow = getNextRow (
                            firstRow,
                            leftCol,
                            lastRow,
                            rightCol,
                            currentRow,
                            currentCol
                    );

                    int nextCol = getNextCol (
                            firstRow,
                            leftCol,
                            lastRow,
                            rightCol,
                            currentRow,
                            currentCol
                    );

                    int temp = matrix[nextRow][nextCol];
                    matrix[nextRow][nextCol] = prev;
                    prev = temp;
                    currentRow = nextRow;
                    currentCol = nextCol;

                } while (!(currentRow == firstRow && currentCol == leftCol));
            }

        }
    }



    public static void printMatrix(int[][] matrix) {
        int rowLength = matrix.length;
        int colLength = matrix.length >= 1 ? matrix[0].length : 0;

        for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
            for (int colIndex = 0; colIndex < colLength; colIndex++) {
                System.out.print(matrix[rowIndex][colIndex] + "\t");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        MultiIndexRotation.rotateMatrix(2, matrixOne);
        System.out.println(" ========== Rotated Matrix One :: Index 2 ========== ");
        printMatrix(matrixOne);

        int[][] matrixTwo = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        MultiIndexRotation.rotateMatrix(3, matrixTwo);
        System.out.println(" ========== Rotated Matrix Two :: Index 3 ========== ");
        printMatrix(matrixTwo);

        matrixOne = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        SingleIndexRotation.rotateMatrix(matrixOne);
        System.out.println(" ========== Rotated Matrix One :: Single Index ========== ");
        printMatrix(matrixOne);

        matrixTwo = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        SingleIndexRotation.rotateMatrix(matrixTwo);
        System.out.println(" ========== Rotated Matrix Two :: Single Index ========== ");
        printMatrix(matrixTwo);
    }
}

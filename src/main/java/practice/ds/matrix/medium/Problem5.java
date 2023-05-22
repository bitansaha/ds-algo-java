package practice.ds.matrix.medium;

import java.util.Optional;

// Given a matrix of ‘O’ and ‘X’, find the largest subsquare surrounded by ‘X’
// https://www.geeksforgeeks.org/given-matrix-o-x-find-largest-subsquare-surrounded-x/

public class Problem5 {

    // Time-Complexity :: O(n^4)
    // Space-Complexity :: O(1)
    public static class StrategyOne {
        public static boolean isSubSequence(int rows, int rowIndex, int startColIndex, int endColIndex, char[][] matrix) {
            int length = endColIndex - startColIndex + 1;
            int endRowIndex = rowIndex + length - 1;

            if (endRowIndex <= rows) {
                boolean isSubSequence = true;

                for (int rightRowIndex = rowIndex; rightRowIndex <= endRowIndex; rightRowIndex++) {
                    isSubSequence = isSubSequence && matrix[rightRowIndex][endColIndex] == 'X';
                }

                for (int bottomColIndex = startColIndex; bottomColIndex <= endColIndex; bottomColIndex++) {
                    isSubSequence = isSubSequence && matrix[endRowIndex][bottomColIndex] == 'X';
                }

                for (int leftRowIndex = rowIndex; leftRowIndex <= endRowIndex; leftRowIndex++) {
                    isSubSequence = isSubSequence && matrix[leftRowIndex][startColIndex] == 'X';
                }

                return isSubSequence;
            }

            return false;
        }
        public static Optional<LargestSubSequence> getLargestSubSequence(char[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            Optional<LargestSubSequence> optionalLargestSubSequence = Optional.empty();

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    if (matrix[rowIndex][colIndex] == 'X') {
                        int startColIndex = colIndex;
                        for (int endColIndex = colIndex; endColIndex <= cols; endColIndex++) {
                            if (matrix[rowIndex][endColIndex] == 'O') {
                                break;
                            } else if (endColIndex - startColIndex + 1 <= optionalLargestSubSequence.map(lss -> lss.length).orElse(0)) {
                                continue;
                            } else if (isSubSequence(rows, rowIndex, startColIndex, endColIndex, matrix)) {
                                optionalLargestSubSequence = Optional
                                        .of (
                                                new LargestSubSequence(rowIndex, startColIndex, endColIndex - startColIndex + 1)
                                        );
                            }
                        }
                    }
                }
            }

            return optionalLargestSubSequence;
        }
    }

    // Time-Complexity :: O(n^3)
    // Space-Complexity :: O(n^2)
    public static class StrategyTwo {
        public static Optional<LargestSubSequence> getLargestSubSequence(char[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            int[][] horDpMatrix = new int[rows + 1][cols + 1];
            int[][] verDpMatrix = new int[rows + 1][cols + 1];

            Optional<LargestSubSequence> optionalLargestSubSequence = Optional.empty();

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    if (matrix[rowIndex][colIndex] == 'O') {
                        horDpMatrix[rowIndex][colIndex] = 0;
                        verDpMatrix[rowIndex][colIndex] = 0;
                    } else {
                        horDpMatrix[rowIndex][colIndex] = (colIndex > 0 ? horDpMatrix[rowIndex][colIndex - 1] : 0) + 1;
                        verDpMatrix[rowIndex][colIndex] = (rowIndex > 0 ? verDpMatrix[rowIndex - 1][colIndex] : 0) + 1;
                    }
                }
            }

            for (int rowIndex = rows; rowIndex >= 0; rowIndex--) {
                for (int colIndex = cols; colIndex >= 0; colIndex--) {
                    int length = Math.min(horDpMatrix[rowIndex][colIndex], verDpMatrix[rowIndex][colIndex]);
                    for (
                            int currentLength = length;
                            currentLength > 0 && currentLength > optionalLargestSubSequence.map(lss -> lss.length).orElse(0);
                            currentLength--
                        ) {
                        int rightTopRowIndex = rowIndex - currentLength + 1;
                        int leftBottomColIndex = colIndex - currentLength + 1;

                        if (rightTopRowIndex < 0 || leftBottomColIndex < 0) continue;

                        if (horDpMatrix[rightTopRowIndex][colIndex] >= currentLength && verDpMatrix[rowIndex][leftBottomColIndex] >= currentLength) {
                            optionalLargestSubSequence = Optional
                                    .of (
                                            new LargestSubSequence(rightTopRowIndex, leftBottomColIndex, currentLength)
                                    );
                        }
                    }
                }
            }

            return optionalLargestSubSequence;

        }
    }


    public static void main(String[] args) {
        char[][] matrixOne = {
                {'X', 'O', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'O', 'X', 'O'},
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'O', 'O'},
        };

        {
            Optional<LargestSubSequence> optionalLargestSubSequenceOne = StrategyOne.getLargestSubSequence(matrixOne);

            System.out.println(" ========== Strategy ONE Matrix ONE =========== ");
            if (optionalLargestSubSequenceOne.isPresent()) {
                System.out.println("start_index = " + optionalLargestSubSequenceOne.get().startRow + ", start_col = " + optionalLargestSubSequenceOne.get().startCol + ", length = " + optionalLargestSubSequenceOne.get().length);
            } else {
                System.out.println("no sub-sequence found");
            }
        }

        // ======================== MATRIX TWO

        char[][] matrixTwo = {
                {'X', 'O', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'O', 'X', 'O'},
        };

        {
            Optional<LargestSubSequence> optionalLargestSubSequenceTwo = StrategyOne.getLargestSubSequence(matrixTwo);

            System.out.println(" ========== Strategy ONE Matrix TWO =========== ");
            if (optionalLargestSubSequenceTwo.isPresent()) {
                System.out.println("start_index = " + optionalLargestSubSequenceTwo.get().startRow + ", start_col = " + optionalLargestSubSequenceTwo.get().startCol + ", length = " + optionalLargestSubSequenceTwo.get().length);
            } else {
                System.out.println("no sub-sequence found");
            }
        }

        // ======================================= Strategy TWO =========================================

        {
            Optional<LargestSubSequence> optionalLargestSubSequenceOne = StrategyTwo.getLargestSubSequence(matrixOne);

            System.out.println(" ========== Strategy TWO Matrix ONE =========== ");
            if (optionalLargestSubSequenceOne.isPresent()) {
                System.out.println("start_index = " + optionalLargestSubSequenceOne.get().startRow + ", start_col = " + optionalLargestSubSequenceOne.get().startCol + ", length = " + optionalLargestSubSequenceOne.get().length);
            } else {
                System.out.println("no sub-sequence found");
            }
        }

        {
            Optional<LargestSubSequence> optionalLargestSubSequenceTwo = StrategyTwo.getLargestSubSequence(matrixTwo);

            System.out.println(" ========== Strategy TWO Matrix TWO =========== ");
            if (optionalLargestSubSequenceTwo.isPresent()) {
                System.out.println("start_index = " + optionalLargestSubSequenceTwo.get().startRow + ", start_col = " + optionalLargestSubSequenceTwo.get().startCol + ", length = " + optionalLargestSubSequenceTwo.get().length);
            } else {
                System.out.println("no sub-sequence found");
            }
        }

    }

    public static class LargestSubSequence {
        public int startRow;
        public int startCol;
        public int length;

        public LargestSubSequence(int startRow, int startCol, int length) {
            this.startRow = startRow;
            this.startCol = startCol;
            this.length = length;
        }
    }
}

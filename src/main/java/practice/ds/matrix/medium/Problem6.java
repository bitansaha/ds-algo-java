package practice.ds.matrix.medium;

import java.util.Optional;

// Maximum size square sub-matrix with all 1s
// https://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
public class Problem6 {

    // Time-Complexity :: O(n^4)
    // Space-Complexity :: O(1)
    public static class SolutionOne {
        public static boolean isSubSquare(int startRowIndex, int startColIndex, int endColIndex, int[][] matrix) {
            int rows = matrix.length - 1;
            int endRowIndex = startRowIndex + (endColIndex - startColIndex);
            if (endRowIndex <= rows) {
                boolean isAllOne = true;
                for (int rowIndex = startRowIndex + 1; rowIndex <= endRowIndex; rowIndex++) {
                    for (int colIndex = startColIndex; colIndex <= endColIndex; colIndex++) {
                        isAllOne = isAllOne && (matrix[rowIndex][colIndex] == 1);
                    }
                }
                return isAllOne;
            }

            return false;
        }

        public static Optional<LargestSubSquare> getLargestSubSquare(int[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            Optional<LargestSubSquare> optionalLargestSubSquare = Optional.empty();

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    if (matrix[rowIndex][colIndex] == 1) {
                        int startColIndex = colIndex;
                        for (int endColIndex = colIndex; endColIndex <= cols; endColIndex++) {
                            int length = endColIndex - startColIndex + 1;
                            if (matrix[rowIndex][endColIndex] == 0) {
                                break;
                            } else if (length < optionalLargestSubSquare.map(lss -> lss.length).orElse(0)) {
                                continue;
                            } else if (isSubSquare(rowIndex, startColIndex, endColIndex, matrix)) {
                                optionalLargestSubSquare = Optional
                                        .of(
                                                new LargestSubSquare(rowIndex, startColIndex, length)
                                        );
                            }
                        }
                    }
                }
            }

            return optionalLargestSubSquare;
        }
    }

    // Time-Complexity :: O(n^3)
    // Space-Complexity :: O(n^2)
    public static class SolutionTwo {
        public static Optional<LargestSubSquare> getLargestSubSquare(int[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            Optional<LargestSubSquare> optionalLargestSubSquare = Optional.empty();
            int[][] horDPMatrix = new int[rows + 1][cols + 1];

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    if (matrix[rowIndex][colIndex] == 0) {
                        horDPMatrix[rowIndex][colIndex] = 0;
                    } else {
                        horDPMatrix[rowIndex][colIndex] = colIndex > 0 ? horDPMatrix[rowIndex][colIndex - 1] + 1 : 1;
                    }
                }
            }

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    if (horDPMatrix[rowIndex][colIndex] > optionalLargestSubSquare.map(lss -> lss.length).orElse(0)) {
                        int expectedLength = horDPMatrix[rowIndex][colIndex];
                        for (int scanRowIndex = rowIndex + expectedLength - 1; scanRowIndex >= rowIndex; scanRowIndex--) {
                            if (scanRowIndex > rows) {
                                expectedLength--;
                            } else if (horDPMatrix[scanRowIndex][colIndex] == 0) {
                                expectedLength = scanRowIndex - rowIndex;
                            } else if (horDPMatrix[scanRowIndex][colIndex] < expectedLength) {
                                expectedLength = horDPMatrix[scanRowIndex][colIndex];
                            }
                        }

                        if (expectedLength > optionalLargestSubSquare.map(lss -> lss.length).orElse(0)) {
                            optionalLargestSubSquare = Optional
                                    .of (
                                            new LargestSubSquare(rowIndex, colIndex - expectedLength + 1, expectedLength)
                                    );
                        }
                    }
                }
            }

            return optionalLargestSubSquare;

        }
    }

    // Time-Complexity :: O(n^2)
    // Space-Complexity :: O(n^2)
    public static class SolutionThree {
        public static Optional<LargestSubSquare> getLargestSubSquare(int[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            Optional<LargestSubSquare> optionalLargestSubSquare = Optional.empty();
            int[][] dpMatrix = new int[rows + 1][cols + 1];

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                   if (matrix[rowIndex][colIndex] == 0) {
                       dpMatrix[rowIndex][colIndex] = 0;
                   } else {
                       dpMatrix[rowIndex][colIndex] = Math
                               .min (
                                    rowIndex - 1 >= 0 ? dpMatrix[rowIndex - 1][colIndex] : 0,
                                    Math
                                            .min(
                                                    colIndex - 1 >= 0 ? dpMatrix[rowIndex][colIndex - 1] : 0,
                                                    rowIndex - 1 >= 0  && colIndex - 1 >= 0 ? dpMatrix[rowIndex - 1][colIndex - 1] : 0
                                            )
                               ) + 1;
                   }
                }
            }

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    if (dpMatrix[rowIndex][colIndex] > optionalLargestSubSquare.map(lss -> lss.length).orElse(0)) {
                        optionalLargestSubSquare = Optional
                                .of(
                                        new LargestSubSquare(
                                                rowIndex - dpMatrix[rowIndex][colIndex] + 1,
                                                colIndex - dpMatrix[rowIndex][colIndex] + 1,
                                                dpMatrix[rowIndex][colIndex]
                                        )
                                );
                    }
                }
            }

            return optionalLargestSubSquare;

        }
    }

    // Time-Complexity :: O(n^2)
    // Space-Complexity :: O(n)
    public static class SolutionFour {
        private static int getCurrentRowIndex(int rowIndex) {
            return rowIndex % 2;
        }

        private static int getLastRowIndex(int rowIndex) {
            if (getCurrentRowIndex(rowIndex) == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        public static Optional<LargestSubSquare> getLargestSubSquare(int[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            Optional<LargestSubSquare> optionalLargestSubSquare = Optional.empty();
            int[][] dpMatrix = new int[2][cols + 1];

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    if (matrix[rowIndex][colIndex] == 0) {
                        dpMatrix[getCurrentRowIndex(rowIndex)][colIndex] = 0;
                    } else {
                        dpMatrix[getCurrentRowIndex(rowIndex)][colIndex] = Math
                                .min (
                                        rowIndex - 1 >= 0 ? dpMatrix[getLastRowIndex(rowIndex)][colIndex] : 0,
                                        Math
                                                .min(
                                                        colIndex - 1 >= 0 ? dpMatrix[getCurrentRowIndex(rowIndex)][colIndex - 1] : 0,
                                                        rowIndex - 1 >= 0  && colIndex - 1 >= 0 ? dpMatrix[getLastRowIndex(rowIndex)][colIndex - 1] : 0
                                                )
                                ) + 1;

                        if (dpMatrix[getCurrentRowIndex(rowIndex)][colIndex] > optionalLargestSubSquare.map(lss -> lss.length).orElse(0)) {
                            optionalLargestSubSquare = Optional
                                    .of(
                                            new LargestSubSquare(
                                                    rowIndex - dpMatrix[getCurrentRowIndex(rowIndex)][colIndex] + 1,
                                                    colIndex - dpMatrix[getCurrentRowIndex(rowIndex)][colIndex] + 1,
                                                    dpMatrix[getCurrentRowIndex(rowIndex)][colIndex]
                                            )
                                    );
                        }
                    }
                }
            }

            return optionalLargestSubSquare;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 0, 1, 1, 0, 1 },
                { 1, 1, 0, 1, 0 },
                { 0, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0 }
        };

        System.out.println(" ============== Solution ONE ============= ");
        {
            Optional<LargestSubSquare> optionalLargestSubSquare = SolutionOne.getLargestSubSquare(matrix);
            if (optionalLargestSubSquare.isPresent()) {
                System.out.println("start_row: " + optionalLargestSubSquare.get().startRow + ", start_col: " + optionalLargestSubSquare.get().startCol + ", length: " + optionalLargestSubSquare.get().length);
            } else {
                System.out.println("!!!!!!!NOT FOUND!!!!!!!");
            }
        }

        System.out.println(" ============== Solution TWO ============= ");
        {
            Optional<LargestSubSquare> optionalLargestSubSquare = SolutionTwo.getLargestSubSquare(matrix);
            if (optionalLargestSubSquare.isPresent()) {
                System.out.println("start_row: " + optionalLargestSubSquare.get().startRow + ", start_col: " + optionalLargestSubSquare.get().startCol + ", length: " + optionalLargestSubSquare.get().length);
            } else {
                System.out.println("!!!!!!!NOT FOUND!!!!!!!");
            }
        }

        System.out.println(" ============== Solution THREE ============= ");
        {
            Optional<LargestSubSquare> optionalLargestSubSquare = SolutionThree.getLargestSubSquare(matrix);
            if (optionalLargestSubSquare.isPresent()) {
                System.out.println("start_row: " + optionalLargestSubSquare.get().startRow + ", start_col: " + optionalLargestSubSquare.get().startCol + ", length: " + optionalLargestSubSquare.get().length);
            } else {
                System.out.println("!!!!!!!NOT FOUND!!!!!!!");
            }
        }

        System.out.println(" ============== Solution FOUR ============= ");
        {
            Optional<LargestSubSquare> optionalLargestSubSquare = SolutionFour.getLargestSubSquare(matrix);
            if (optionalLargestSubSquare.isPresent()) {
                System.out.println("start_row: " + optionalLargestSubSquare.get().startRow + ", start_col: " + optionalLargestSubSquare.get().startCol + ", length: " + optionalLargestSubSquare.get().length);
            } else {
                System.out.println("!!!!!!!NOT FOUND!!!!!!!");
            }
        }
    }

    public static class LargestSubSquare {
        public int startRow;
        public int startCol;
        public int length;

        public LargestSubSquare(int startRow, int startCol, int length) {
            this.startRow = startRow;
            this.startCol = startCol;
            this.length = length;
        }
    }
}

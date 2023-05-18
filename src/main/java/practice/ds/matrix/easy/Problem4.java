package practice.ds.matrix.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Problem4 {

    public static class StrategyOne {

        // Sort all Rows of the Matrix
        // Iterate through each index of the first row and do binary search on the rest of the rows to search the value

        // Time-Complexity :: O(n^2*log(n)) + O(n^2*log(n)) = O(n^2*log(n))
        // Space-Complexity :: O(1)
        public static boolean isPresent(int rowIndex, int value, int[][] matrix) {
            int cols = matrix[rowIndex].length - 1;

            int startIndex = 0;
            int endIndex = cols;

            while (startIndex <= endIndex) {
                int midIndex = startIndex + (((endIndex - startIndex) + 1) / 2);

                if (matrix[rowIndex][midIndex] == value) {
                    return true;
                } else if (matrix[rowIndex][midIndex] > value) {
                    endIndex = midIndex - 1;
                } else {
                    startIndex = midIndex + 1;
                }

            }

            return false;
        }

        public static void printCommonValue(int[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            if (rows == 0) return;

            // Time-Complexity :: O(n^2log(n))
            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                Arrays.sort(matrix[rowIndex]);
            }

            // Time-Complexity :: O(n^2log(n))
            masterLoop:
            for (int firstRowColIndex = 0; firstRowColIndex <= cols; firstRowColIndex++) {
                int value = matrix[0][firstRowColIndex];

                for (int rowIndex = 1; rowIndex <= rows; rowIndex++) {
                    if (!isPresent(rowIndex, value, matrix)) continue masterLoop;
                }

                System.out.print(value + " ");
            }
        }
    }

    public static class StrategyTwo {

        // Time-Complexity :: O(n^2*log(n)) + O(n^2) = O(n^2*log(n))
        // Space-Complexity :: O(1)
        public static void printCommonValue(int[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            if (rows == 0) return;

            // Time-Complexity :: O(n^2log(n))
            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                Arrays.sort(matrix[rowIndex]);
            }

            int[] rowIndexArray = new int[matrix.length];

            // Time-Complexity :: O(n^2)
            masterLoop:
            for (int firstRowColIndex = 0; firstRowColIndex <= cols; firstRowColIndex++) {
                int value = matrix[0][firstRowColIndex];

                for (int searchRowIndex = 1; searchRowIndex <= rows; searchRowIndex++) {
                    while (rowIndexArray[searchRowIndex] <= cols && matrix[searchRowIndex][rowIndexArray[searchRowIndex]] <= value) {
                        rowIndexArray[searchRowIndex]++;
                    }

                    if (matrix[searchRowIndex][rowIndexArray[searchRowIndex] == 0 ? rowIndexArray[searchRowIndex] : rowIndexArray[searchRowIndex] - 1] != value) {
                       if (rowIndexArray[searchRowIndex] > cols) {
                           return;
                       } else {
                           continue masterLoop;
                       }
                    }
                }

                System.out.print(value + " ");
            }
        }
    }

    public static class StrategyThree {
        // Time-Complexity :: O(n^2)
        // Space-Complexity :: O(n)
        public static void printCommonValue(int[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            HashSet<Integer> firstRowValueSet = new HashSet<>();
            HashSet<Integer> rowValueSet = new HashSet<>();

            for (int firstRowColIndex = 0; firstRowColIndex <= cols; firstRowColIndex++) {
                firstRowValueSet.add(matrix[0][firstRowColIndex]);
            }

            for (int rowIndex = 1; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    rowValueSet.add(matrix[rowIndex][colIndex]);
                }

                for (int firstRowValue : firstRowValueSet.toArray(new Integer[firstRowValueSet.size()])) {
                    if (!rowValueSet.contains(firstRowValue)) firstRowValueSet.remove(firstRowValue);
                }

                rowValueSet = new HashSet<>();
            }

            firstRowValueSet
                    .forEach(value -> System.out.print(value + " "));
        }
    }


    public static void main(String[] args) {
        int[][] matrixOne = {
                {2, 1, 4, 3},
                {1, 2, 3, 2},
                {3, 6, 2, 3},
                {5, 2, 5, 3}
        };
        System.out.println(" ======== Strategy One MatrixONE Common Value ======== ");
        StrategyOne.printCommonValue(matrixOne);
        System.out.println("");

        int[][] matrixTwo = {
                {12, 1, 14, 3, 16},
                {14, 2, 1, 3, 35},
                {14, 1, 14, 3, 11},
                {14, 25, 3, 2, 1},
                {1, 18, 3, 21, 14}
        };
        System.out.println(" ======== Strategy One MatrixTWO Common Value ======== ");
        StrategyOne.printCommonValue(matrixTwo);

        System.out.println("");
        // ========== Strategy TWO ==========

        System.out.println(" ======== Strategy Two MatrixONE Common Value ======== ");
        StrategyTwo.printCommonValue(matrixOne);
        System.out.println("");

        System.out.println(" ======== Strategy Two MatrixTWO Common Value ======== ");
        StrategyTwo.printCommonValue(matrixTwo);

        System.out.println("");
        // ========== Strategy Three ==========

        System.out.println(" ======== Strategy Three MatrixONE Common Value ======== ");
        StrategyThree.printCommonValue(matrixOne);
        System.out.println("");

        System.out.println(" ======== Strategy Three MatrixTWO Common Value ======== ");
        StrategyThree.printCommonValue(matrixTwo);

    }
}

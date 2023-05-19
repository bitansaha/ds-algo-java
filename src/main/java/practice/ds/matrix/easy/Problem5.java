package practice.ds.matrix.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem5 {

    public static class StrategyTwo {
        // Time Complexity :: O(n^3)
        // Space Complexity :: O(n)
        public static void printUniqueElements(int[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            int rowLength = matrix.length;

            // Time Complexity :: O(n^2 log(n))
            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                Arrays.sort(matrix[rowIndex]);
            }

            int[] rowIndexArray;

            // Time Complexity :: O(n^3)
            // Space Complexity :: O(n)
            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                colLoop:
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    int value = matrix[rowIndex][colIndex];

                    if (
                        (colIndex - 1 >= 0 && matrix[rowIndex][colIndex - 1] == value) ||
                                (colIndex + 1 <= cols && matrix[rowIndex][colIndex + 1] == value)
                    ) {
                        continue;
                    }

                    rowIndexArray = new int[rowLength];

                    for (int searchRowIndex = 0; searchRowIndex <= rows; searchRowIndex++) {
                        if (searchRowIndex == rowIndex) continue;

                        while (rowIndexArray[searchRowIndex] <= cols && matrix[searchRowIndex][rowIndexArray[searchRowIndex]] <= value) {
                            rowIndexArray[searchRowIndex]++;
                        }

                        if (matrix[searchRowIndex][rowIndexArray[searchRowIndex] == 0 ? rowIndexArray[searchRowIndex] : rowIndexArray[searchRowIndex] - 1] == value) {
                            continue colLoop;
                        }
                    }

                    System.out.print(value + " ");
                }
            }
        }
    }

    public static class StrategyOne {
        // Time-Complexity :: O(n^2)
        // Space-Complexity :: O(n^2)
        public static void printUniqueElements(int[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            Map<Integer, Integer> valueCountMap = new HashMap<>();

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    if (valueCountMap.containsKey(matrix[rowIndex][colIndex])) {
                        valueCountMap.put(matrix[rowIndex][colIndex], valueCountMap.get(matrix[rowIndex][colIndex]) + 1);
                    } else {
                        valueCountMap.put(matrix[rowIndex][colIndex], 1);
                    }
                }
            }

            for (int value : valueCountMap.keySet()) {
                if (valueCountMap.get(value) == 1) {
                    System.out.print(value + " ");
                }
            }

        }
    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {20, 15, 30, 2},
                {2, 3, 5, 30},
                {6, 7, 6, 8}
        };
        System.out.println(" ======== StrategyONE Matrix ONE ======== ");
        StrategyOne.printUniqueElements(matrixOne);
        System.out.println("\n ========  ======== \n");


        int[][] matrixTwo = {
                {1, 2, 3},
                {5, 6, 2},
                {1, 3, 5},
                {6, 2, 2}
        };
        System.out.println(" ======== StrategyONE Matrix TWO ======== ");
        StrategyOne.printUniqueElements(matrixTwo);
        System.out.println("\n ========  ======== \n");

        System.out.println(" ======== StrategyTWO Matrix ONE ======== ");
        StrategyTwo.printUniqueElements(matrixOne);
        System.out.println("\n ========  ======== \n");

        System.out.println(" ======== StrategyTWO Matrix TWO ======== ");
        StrategyTwo.printUniqueElements(matrixTwo);
        System.out.println("\n ========  ======== \n");


    }
}

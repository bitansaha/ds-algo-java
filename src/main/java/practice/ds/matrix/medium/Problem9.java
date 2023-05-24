package practice.ds.matrix.medium;

import java.util.Arrays;
import java.util.HashMap;

// Find pairs with given sum such that elements of pair are in different rows
// https://www.geeksforgeeks.org/find-pairs-given-sum-elements-pair-different-rows/#
public class Problem9 {

    // Time-Complexity :: O(n^3*log(n))
    // Space-Complexity :: O(1)
    public static class SolutionOne {

        public static boolean search(int[] array, int searchTerm) {
            int leftIndex = 0;
            int rightIndex = array.length - 1;
            while (leftIndex <= rightIndex) {
                int midIndex = leftIndex + ((rightIndex - leftIndex + 1)/2);

                if (array[midIndex] == searchTerm) {
                    return true;
                } else if (array[midIndex] < searchTerm) {
                    leftIndex = midIndex + 1;
                } else {
                    rightIndex = midIndex - 1;
                }
            }

            return false;
        }

        public static void findPair(int[][] matrix, int sum) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                Arrays.sort(matrix[rowIndex]);
            }

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    int searchTerm = sum - matrix[rowIndex][colIndex];
                    for (int searchRowIndex = rowIndex + 1; searchRowIndex <= rows; searchRowIndex++) {
                        if (searchRowIndex != rowIndex && search(matrix[searchRowIndex], searchTerm)) {
                            System.out.println("(" + matrix[rowIndex][colIndex] + ", " + searchTerm + ")");
                        }
                    }
                }
            }

        }
    }

    // Time-Complexity :: O(n^2)
    // Space-Complexity :: O(n^2)
    public static class SolutionTwo {

        public static void findPair(int[][] matrix, int sum) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            HashMap<Integer, Integer> valueRowMap = new HashMap<>();

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    valueRowMap.put(matrix[rowIndex][colIndex], rowIndex);
                }
            }

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    int searchTerm = sum - matrix[rowIndex][colIndex];
                    if (valueRowMap.containsKey(searchTerm) && valueRowMap.get(searchTerm) > rowIndex) {
                        System.out.println("(" + matrix[rowIndex][colIndex] + ", " + searchTerm + ")");
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {1, 3, 2, 4},
                {5, 8, 7, 6},
                {9, 10, 13, 11},
                {12, 0, 14, 15}
        };

        System.out.println(" ============ Solution ONE ============= ");
        SolutionOne.findPair(matrixOne, 11);

        int[][] matrixTwo = {
                {1, 3, 2, 4},
                {5, 8, 7, 6},
                {9, 10, 13, 11},
                {12, 0, 14, 15}
        };

        System.out.println(" ============ Solution TWO ============= ");
        SolutionTwo.findPair(matrixTwo, 11);
    }
}

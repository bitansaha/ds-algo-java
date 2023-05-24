package practice.ds.matrix.medium;

import java.util.HashMap;

// Find all permuted rows of a given row in a matrix
// https://www.geeksforgeeks.org/find-permuted-rows-given-row-matrix/
public class Problem10 {

    // Time-Complexity :: O(n^2)
    // Space-Complexity :: O(n)
    public static void findPermutedRows(int[][] matrix, int rowNumber) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        HashMap<Integer, int[]> rowValueCountMap = new HashMap<>();

        for (int colIndex = 0; colIndex <= cols; colIndex++) {
            if (rowValueCountMap.containsKey(matrix[rowNumber][colIndex])) {
                rowValueCountMap.get(matrix[rowNumber][colIndex])[0]++;
            } else {
                rowValueCountMap.put(matrix[rowNumber][colIndex], new int[] {1, 0});
            }
        }

        rowLoop:
        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                if (rowIndex == rowNumber) continue rowLoop;

                if (!rowValueCountMap.containsKey(matrix[rowIndex][colIndex])) {
                    rowValueCountMap.values().forEach(array -> array[1] = 0);
                    continue rowLoop;
                } else {
                    rowValueCountMap.get(matrix[rowIndex][colIndex])[1]++;
                }
            }

            int similarityCount = 0;

            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                similarityCount += rowValueCountMap.get(matrix[rowIndex][colIndex])[0] == rowValueCountMap.get(matrix[rowIndex][colIndex])[1] ? 1 : 0;
            }

            if (similarityCount == cols + 1) {
                System.out.print(rowIndex + ", ");
            }

            rowValueCountMap.values().forEach(array -> array[1] = 0);
        }
    }

    public static void main(String[] args) {

        int[][] matrix = {
                {3, 1, 4, 2},
                {1, 6, 9, 3},
                {1, 2, 3, 4},
                {4, 3, 2, 1}
        };
        int rowNumber = 3;

        findPermutedRows(matrix, rowNumber);
    }
}

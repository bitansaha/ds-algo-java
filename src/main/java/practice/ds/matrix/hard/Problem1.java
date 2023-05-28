package practice.ds.matrix.hard;

// Find the number of islands using DFS
// https://www.geeksforgeeks.org/find-the-number-of-islands-using-dfs/
public class Problem1 {

    // Time-Complexity :: O(m*n)
    // Space-Complexity :: O(m*n)
    public static void fillIsland(int[][] matrix, int row, int col) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        if (row >= 0 && row <= rows && col >= 0 && col <= cols) {
            if (matrix[row][col] == 1) {
                matrix[row][col] = -1;

                fillIsland(matrix, row - 1, col - 1);
                fillIsland(matrix, row - 1, col);
                fillIsland(matrix, row - 1, col + 1);
                fillIsland(matrix, row, col + 1);
                fillIsland(matrix, row + 1, col + 1);
                fillIsland(matrix, row + 1, col);
                fillIsland(matrix, row + 1, col - 1);
                fillIsland(matrix, row, col - 1);
            }
        }
    }

    public static int countIslands(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        int islandCount = 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                if (matrix[rowIndex][colIndex] == 1) {
                    islandCount++;
                    fillIsland(matrix, rowIndex, colIndex);
                }
            }
        }

        return islandCount;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                { 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 1 },
                { 1, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0 },
                { 1, 0, 1, 0, 1 }
        };

        System.out.println("Island Count: " + countIslands(matrix));
    }
}

package practice.ds.matrix.medium;

// https://www.geeksforgeeks.org/find-number-transformation-make-two-matrix-equal/
// Find number of transformation to make two Matrix Equal
public class Problem11 {

    // Time-Complexity :: O(n^2)
    // Space-Complexity :: O(1)
    public static int countSteps(int[][] matrixOne, int[][] matrixTwo) {
        if (
                matrixOne.length != matrixTwo.length
                        &&
                        (matrixOne.length >= 1 ? matrixOne[0].length : 0) != (matrixTwo.length >= 1 ? matrixTwo[0].length : 0)
        )
            return -1;

        int rows = matrixOne.length - 1;
        int cols = matrixOne.length >= 1 ? matrixOne[0].length - 1 : 0;

        for (int rowIndex = 1; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 1; colIndex <= cols; colIndex++) {
                if (
                        (matrixTwo[rowIndex][colIndex] - matrixOne[rowIndex][colIndex])
                        - (matrixTwo[0][colIndex] - matrixOne[0][colIndex])
                        - (matrixTwo[rowIndex][0] - matrixOne[rowIndex][0])
                        + (matrixTwo[0][0] - matrixOne[0][0]) != 0
                ) {
                    return -1;
                }
            }
        }

        int steps = 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            steps += Math.abs(matrixTwo[rowIndex][0] - matrixOne[rowIndex][0]);
        }

        for (int colIndex = 1; colIndex <= cols; colIndex++) {
            steps += Math.abs(matrixTwo[0][colIndex] - matrixOne[0][colIndex]);
        }

        steps -= Math.abs(matrixTwo[0][0] - matrixOne[0][0]);

        return steps;


    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        int[][] matrixTwo = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Steps: " + countSteps(matrixOne, matrixTwo));
    }
}

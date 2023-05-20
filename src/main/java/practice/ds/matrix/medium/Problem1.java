package practice.ds.matrix.medium;

import java.util.Optional;
// Program for Conway’s Game Of Life
// https://www.geeksforgeeks.org/program-for-conways-game-of-life/


public class Problem1 {

    public static class SolutionOne {
        // Time-Complexity :: O(n^2)
        // Space-Complexity :: O(1)
        public static Optional<Integer> getNeighborState(int[][] matrix, int neighborRow, int neighborCol) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            if (neighborRow >= 0 && neighborRow <= rows && neighborCol >= 0 && neighborCol <= cols) {
                int neighborValue = matrix[neighborRow][neighborCol];
                return Optional
                        .of (
                                neighborValue == 1 || neighborValue == 30 || neighborValue == 40 ? 1 : 0
                        );
            }

            return Optional.empty();
        }

        public static int[][] applyCGLNextGeneration(int[][] matrix) {
            // transition stages
            // 0 -> 1 = 10
            // 0 -> 0 = 20
            // 1 -> 1 = 30
            // 1 -> 0 = 40

            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    int currentState = matrix[rowIndex][colIndex];
                    int aliveNeighbourCount = 0;

                    aliveNeighbourCount += getNeighborState(matrix, rowIndex - 1, colIndex - 1).orElse(0);
                    aliveNeighbourCount += getNeighborState(matrix, rowIndex - 1, colIndex).orElse(0);
                    aliveNeighbourCount += getNeighborState(matrix, rowIndex - 1, colIndex + 1).orElse(0);
                    aliveNeighbourCount += getNeighborState(matrix, rowIndex, colIndex + 1).orElse(0);
                    aliveNeighbourCount += getNeighborState(matrix, rowIndex + 1, colIndex + 1).orElse(0);
                    aliveNeighbourCount += getNeighborState(matrix, rowIndex + 1, colIndex).orElse(0);
                    aliveNeighbourCount += getNeighborState(matrix, rowIndex + 1, colIndex - 1).orElse(0);
                    aliveNeighbourCount += getNeighborState(matrix, rowIndex, colIndex - 1).orElse(0);

                    int nextState = 0;

                    if (currentState == 1 && aliveNeighbourCount < 2) {
                        nextState = 40;
                    } else if (currentState == 1 && (aliveNeighbourCount == 2 || aliveNeighbourCount == 3)) {
                        nextState = 30;
                    } else if (currentState == 1 && aliveNeighbourCount > 3) {
                        nextState = 40;
                    } else if (currentState == 0 && aliveNeighbourCount == 3) {
                        nextState = 10;
                    }

                    matrix[rowIndex][colIndex] = nextState;
                }
            }

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    int currentState = matrix[rowIndex][colIndex];

                    if (currentState == 0 || currentState == 1) {
                        continue;
                    } else if (currentState == 10) {
                        matrix[rowIndex][colIndex] = 1;
                    } else if (currentState == 20) {
                        matrix[rowIndex][colIndex] = 0;
                    } else if (currentState == 30) {
                        matrix[rowIndex][colIndex] = 1;
                    } else if (currentState == 40) {
                        matrix[rowIndex][colIndex] = 0;
                    }
                }
            }

            return matrix;
        }

    }

    public static class SolutionTwo {
        // Time-Complexity :: O(n^2)
        // Space-Complexity :: O(n)
        public static int[][] applyCGLNextGeneration(int[][] matrix) {
            int rows = matrix.length - 1;
            int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;
            int colLength = cols + 1;

            int[][] stateBuffer = new int[2][colLength];

            for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    stateBuffer[1][colIndex] = stateBuffer[0][colIndex];
                    stateBuffer[0][colIndex] = matrix[rowIndex][colIndex];
                }

                for (int colIndex = 0; colIndex <= cols; colIndex++) {
                    int currentState = stateBuffer[0][colIndex];
                    int activeNeighborCount = 0;

                    activeNeighborCount += colIndex > 0 ? stateBuffer[1][colIndex - 1] : 0;
                    activeNeighborCount += stateBuffer[1][colIndex];
                    activeNeighborCount += colIndex < cols ? stateBuffer[1][colIndex + 1] : 0;
                    activeNeighborCount += colIndex < cols ? stateBuffer[0][colIndex + 1] : 0;
                    activeNeighborCount += colIndex < cols && rowIndex < rows ? matrix[rowIndex + 1][colIndex + 1] : 0;
                    activeNeighborCount += rowIndex < rows ? matrix[rowIndex + 1][colIndex] : 0;
                    activeNeighborCount += colIndex > 0 && rowIndex < rows ? matrix[rowIndex + 1][colIndex - 1] : 0;
                    activeNeighborCount += colIndex > 0 ? stateBuffer[0][colIndex - 1] : 0;

                    if (currentState == 1 && activeNeighborCount < 2) {
                        matrix[rowIndex][colIndex] = 0;
                    } else if (currentState == 1 && (activeNeighborCount == 2 || activeNeighborCount == 3)) {
                        matrix[rowIndex][colIndex] = 1;
                    } else if (currentState == 1 && activeNeighborCount > 3) {
                        matrix[rowIndex][colIndex] = 0;
                    } else if (currentState == 0 && activeNeighborCount == 3) {
                        matrix[rowIndex][colIndex] = 1;
                    }
                }
            }

            return matrix;

        }
    }


    public static void printMatrix (int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix[0].length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                System.out.print(matrix[rowIndex][colIndex] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int[][] matrixOne = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        System.out.println(" ============ Solution ONE ============ ");
        printMatrix(SolutionOne.applyCGLNextGeneration(matrixOne));

        int[][] matrixTwo = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        System.out.println(" ============ Solution TWO ============ ");
        printMatrix(SolutionTwo.applyCGLNextGeneration(matrixTwo));
    }
}

package practice.ds.matrix.hard;
// Matrix Chain Multiplication | DP-8
// https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
public class Problem3 {

    // Time-Complexity :: O(n^3)
    // Space-Complexity :: O(n^2)
    public static int minimizeMatrixChainMultiplication(int[] matrixDimensions) {
        int[][] cache = new int[matrixDimensions.length][matrixDimensions.length];

        for (int mmLength = 2; mmLength < matrixDimensions.length; mmLength++) {
            for (int startMatrixIndex = 1, endMatrixIndex = mmLength; endMatrixIndex < matrixDimensions.length; startMatrixIndex++, endMatrixIndex++) {
                int minMultiplication = Integer.MAX_VALUE;

                for (int split = startMatrixIndex; split < endMatrixIndex; split++) {
                    minMultiplication = Math
                            .min (
                                    minMultiplication,
                                    cache[startMatrixIndex][split] + cache[split + 1][endMatrixIndex] + matrixDimensions[startMatrixIndex - 1] * matrixDimensions[split] * matrixDimensions[endMatrixIndex]
                            );
                }

                cache[startMatrixIndex][endMatrixIndex] = minMultiplication;
            }
        }

        return cache[1][matrixDimensions.length - 1];
    }

    public static void main(String[] args) {
        int[] matrixDimensionsOne = { 1, 2, 3, 4, 3 };
        System.out.println("min matrix mul one: " + minimizeMatrixChainMultiplication(matrixDimensionsOne));

        int[] matrixDimensionsTwo = {1, 2, 3, 4};
        System.out.println("min matrix mul two: " + minimizeMatrixChainMultiplication(matrixDimensionsTwo));
    }
}

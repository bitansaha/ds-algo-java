package practice.ds.matrix.hard;

import java.util.Stack;

// Maximum size rectangle binary sub-matrix with all 1s
// https://www.geeksforgeeks.org/maximum-size-rectangle-binary-sub-matrix-1s/
public class Problem4 {

    // Time-Complexity :: O(m*n)
    // Space-Complexity :: O(n)
    public static int maxOneRectangle(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int maxOneRectangle = Integer.MIN_VALUE;

        for (int currentIndex = 0; currentIndex < array.length; currentIndex++) {
            int currentHeight = array[currentIndex];

            while (!stack.isEmpty() && array[stack.peek()] > currentHeight) {
                int poppedIndex = stack.pop();
                int poppedHeight = array[poppedIndex];

                if (!stack.isEmpty()) {
                    int previousIndex = stack.peek();
                    int previousHeight = array[previousIndex];

                    maxOneRectangle = Math.max(Math.min(previousHeight, poppedHeight) * (poppedIndex - previousIndex + 1), maxOneRectangle);
                    maxOneRectangle = Math.max(Math.min(previousHeight, currentHeight) * (currentIndex - previousIndex + 1), maxOneRectangle);
                }

                maxOneRectangle = Math.max(Math.min(poppedHeight, currentHeight) * (currentIndex - poppedIndex + 1), maxOneRectangle);
            }

            stack.push(currentIndex);

        }

        if (!stack.isEmpty()) {
            int lastIndex = stack.pop();
            int lastHeight = array[lastIndex];

            while (!stack.isEmpty()) {
                int poppedIndex = stack.pop();
                int poppedHeight = array[poppedIndex];

                if (!stack.isEmpty()) {
                    int previousIndex = stack.peek();
                    int previousHeight = array[previousIndex];

                    maxOneRectangle = Math.max(Math.min(previousHeight, poppedHeight) * (poppedIndex - previousIndex + 1), maxOneRectangle);
                    maxOneRectangle = Math.max(Math.min(previousHeight, lastHeight) * (lastIndex - previousIndex + 1), maxOneRectangle);
                }

                maxOneRectangle = Math.max(Math.min(poppedHeight, lastHeight) * (lastIndex - poppedIndex + 1), maxOneRectangle);
            }

        }

        return maxOneRectangle;

    }

    public static int maxOneRectangle(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        int maxOneRectangle = Integer.MIN_VALUE;

        for (int rowIndex = 1; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                if (matrix[rowIndex][colIndex] == 1) {
                    matrix[rowIndex][colIndex] = matrix[rowIndex - 1][colIndex] + 1;
                }
            }
            maxOneRectangle = Math.max(maxOneRectangle, maxOneRectangle(matrix[rowIndex]));
        }

        return maxOneRectangle;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 0, 1, 1, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 1, 1, 0, 0 },
        };

        System.out.println("max rectangle: " + maxOneRectangle(matrix));
    }
}

package practice.ds.array;

import java.util.Arrays;

public class Problem4 {

    /**
     * Find maximum value of Sum( i*arr[i]) with only rotations on given array allowed
     * https://www.geeksforgeeks.org/find-maximum-value-of-sum-iarri-with-only-rotations-on-given-array-allowed/
     */


    public static int findMaxLeftRotatedValueIndexSum (int[] array) {
        int arraySum = Arrays.stream(array).sum();
        int maxDiff = 0;
        int maxDiffRotationIndex = 0;

        for (int index = 0; index < array.length; index++) {
            int currentDiff = (array.length * array[index]) - arraySum;
            if (currentDiff > maxDiff) {
                maxDiff = currentDiff;
                maxDiffRotationIndex = (index + 1) % (array.length - 1);
            }
        }

        int maxSum = 0;

        for (int index = maxDiffRotationIndex, indexPosition = 0; indexPosition < array.length; index = (index + 1) % array.length, indexPosition++) {
            maxSum += indexPosition * array[index];
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("1 --> " + findMaxLeftRotatedValueIndexSum(new int[]{1, 20, 2, 10}));
        System.out.println("2 --> " + findMaxLeftRotatedValueIndexSum(new int[]{10, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }
}

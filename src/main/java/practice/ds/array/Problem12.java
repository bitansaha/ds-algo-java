package practice.ds.array;

import java.util.Arrays;

public class Problem12 {

    /**
     * Rearrange array such that arr[i] >= arr[j] if i is even and arr[i]<=arr[j] if i is odd and j < i
     * https://www.geeksforgeeks.org/rearrange-array-arri-arrj-even-arri/
     */

    public static int[] reArrangeArray(int[] array) {
        if (array.length == 1) return array;

        int midIndex = array.length % 2 == 0 ? array.length/2 : (array.length - 1)/2 + 1;
        Arrays.sort(array);
        int[] reArrangedArray = new int[array.length];

        for (int originalIndex = midIndex, reArrangedEvenIndex = 1; originalIndex < array.length; originalIndex++, reArrangedEvenIndex+=2) {
            reArrangedArray[reArrangedEvenIndex] = array[originalIndex];
        }

        for (int originalIndex = midIndex - 1, reArrangedOddIndex = 0; originalIndex >= 0; originalIndex--, reArrangedOddIndex+=2) {
            reArrangedArray[reArrangedOddIndex] = array[originalIndex];
        }

        return reArrangedArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        System.out.println("Problem 1 --> I/P: " + Arrays.toString(array) + ", O/P: " + Arrays.toString(reArrangeArray(array)));

        array = new int[]{1, 2, 1, 4, 5, 6, 8, 8};
        System.out.println("Problem 2 --> I/P: " + Arrays.toString(array) + ", O/P: " + Arrays.toString(reArrangeArray(array)));
    }
}

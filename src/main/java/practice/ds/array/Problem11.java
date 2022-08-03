package practice.ds.array;

import java.util.Arrays;

public class Problem11 {

    /**
     * Rearrange an array such that arr[i] = i
     * https://www.geeksforgeeks.org/rearrange-array-arri/
     */

    //Time Complexity: O(2n) ~ O(n)
    // Space Complexity: O(1)
    public static void reArrangeArray(int[] array) {
        for (int index = 0; index < array.length; index++) {
            if (!(array[index] == -1 || array[index] == index)) {
                int reArrangeValue = array[index];
                array[index] = -1;
                while (reArrangeValue != -1) {
                    int tmpReArrangeValue = array[reArrangeValue];
                    array[reArrangeValue] = reArrangeValue;
                    reArrangeValue = tmpReArrangeValue;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{-1, -1, 6, 1, 9, 3, 2, -1, 4, -1};
        int[] expectedOutput = new int[]{-1, 1, 2, 3, 4, -1, 6, -1, -1, 9};
        reArrangeArray(array);
        System.out.println("Solution 1: " + Arrays.equals(array, expectedOutput));

        array = new int[]{19, 7, 0, 3, 18, 15, 12, 6, 1, 8, 11, 10, 9, 5, 13, 16, 2, 14, 17, 4};
        expectedOutput = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        reArrangeArray(array);
        System.out.println("Solution 2: " + Arrays.equals(array, expectedOutput));
    }
}

package practice.ds.array;

import java.util.Arrays;

public class Problem14 {

    /**
     * Rearrange positive and negative numbers in O(n) time and O(1) extra space maintaining the order of appearance
     * https://www.geeksforgeeks.org/rearrange-array-alternating-positive-negative-items-o1-extra-space/
     */

    /**
     * Runtime Complexity - O(n^2)
     * Space Complexity - O(1)
     * For each miss match it search the next suitable available value and performs a single rotation (as compared to SWAP)
     * to retain the order
     * @param array
     * @return
     */
    public static int[] reArrangeArrayON2(int[] array) {
        mainLoop:
        for (int index = 0, lastPositiveIndex = -1, lastNegativeIndex = -1; index < array.length; index++) {
            if (index%2 == 0 && array[index] >= 0) {
                // search for Negative Value and Rotate
                for (int negativeValueIndex = Math.max(lastNegativeIndex + 1, index + 1); negativeValueIndex < array.length; negativeValueIndex++) {
                    if (array[negativeValueIndex] < 0) {
                        lastNegativeIndex = negativeValueIndex;
                        singleRotation(index, negativeValueIndex, array);
                        continue mainLoop;
                    }
                }
                break;
            } else if (index%2 != 0 && array[index] < 0) {
                // Search for Positive Value and Rotate
                for (int positiveValueIndex = Math.max(lastPositiveIndex + 1, index + 1); positiveValueIndex < array.length; positiveValueIndex++) {
                    if (array[positiveValueIndex] >= 0) {
                        lastPositiveIndex = positiveValueIndex;
                        singleRotation(index, positiveValueIndex, array);
                        continue mainLoop;
                    }
                }
                break;
            }
        }
        return array;
    }

    public static void singleRotation(int startIndex, int endIndex, int[] array) {
        if (startIndex < endIndex) {
            int previousValue = array[startIndex];
            for (int index = startIndex + 1; index <= endIndex; index++) {
                int temp = array[index];
                array[index] = previousValue;
                previousValue = temp;
            }
            array[startIndex] = previousValue;
        }
    }

    public static Integer[] reArrangeArrayON (Integer[] array) {

        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, -4, -1, 4};
        System.out.println("ON2 Solution Problem ONE --> I/P: " + Arrays.toString(array) + ", O/P: " + Arrays.toString(reArrangeArrayON2(array)));

        array = new int[]{-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        System.out.println("ON2 Solution Problem TWO --> I/P: " + Arrays.toString(array) + ", O/P: " + Arrays.toString(reArrangeArrayON2(array)));

        Integer[] arrayInt = new Integer[]{1, 2, 3, -4, -1, 4};
        System.out.println("ON Solution Problem ONE --> I/P: " + Arrays.toString(arrayInt) + ", O/P: " + Arrays.toString(reArrangeArrayON(arrayInt)));

        arrayInt = new Integer[]{-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        System.out.println("ON Solution Problem TWO --> I/P: " + Arrays.toString(arrayInt) + ", O/P: " + Arrays.toString(reArrangeArrayON(arrayInt)));
    }


}

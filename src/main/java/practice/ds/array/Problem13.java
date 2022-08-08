package practice.ds.array;

import java.util.Arrays;

public class Problem13 {

    /**
     * Rearrange positive and negative numbers in O(n) time and O(1) extra space
     * https://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
     */

    /**
     * Runtime Complexity - O(n)
     * Space Complexity - O(1)
     * Three Index Swap solution
     * @param array
     * @return
     */
    public static int[] reArrangeArrayThreeIndexSwap(int[] array) {
        mainLoop:
        for (int index = 0, negativeSI = -1, positiveSI = -1; index < array.length; index++) {
            if (index%2 == 0) {
                // Index for Negative Values
                if (array[index] >= 0)  {
                    if (negativeSI == -1) negativeSI = index + 1;
                    for (; negativeSI < array.length; negativeSI++) {
                        if (array[negativeSI] < 0) {
                            int temp = array[index];
                            array[index] = array[negativeSI];
                            array[negativeSI] = temp;
                            continue mainLoop;
                        }
                    }
                    break;
                }
            } else {
                // Index for Positive Values
                if (array[index] < 0) {
                    if (positiveSI == -1) positiveSI = index + 1;
                    for (; positiveSI < array.length; positiveSI++) {
                        if (array[positiveSI] >= 0) {
                            int temp = array[index];
                            array[index] = array[positiveSI];
                            array[positiveSI] = temp;
                            continue mainLoop;
                        }
                    }
                    break;
                }
            }
        }

        return array;
    }

    /**
     * Runtime Complexity - O(n)
     * Space Complexity - O(1)
     * Dutch Flag Modified solution
     * @param array
     * @return
     */
    public static int[] reArrangeArrayDutchFlagMod(int[] array) {
        if (array.length == 1) return array;

        int firstPositiveIndex = -1;
        // Dutch Flag Algorithm to segregate Negative and Positive values
        for (int negativeIndex = 0, positiveIndex = array.length - 1; negativeIndex < positiveIndex; ) {
            if (array[negativeIndex] >= 0) {
                int temp = array[negativeIndex];
                array[negativeIndex] = array[positiveIndex];
                array[positiveIndex] = temp;
                firstPositiveIndex = positiveIndex--;
            } else {
                negativeIndex++;
            }
        }

        if (firstPositiveIndex == -1) return array;

        for (int desiredPositiveIndex = 1; array[desiredPositiveIndex] < 0; desiredPositiveIndex += 2) {
            int temp = array[desiredPositiveIndex];
            array[desiredPositiveIndex] = array[firstPositiveIndex];
            array[firstPositiveIndex] = temp;
            firstPositiveIndex++;
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[]{-1, 2, -3, 4, 5, 6, -7, 8, 9};
        System.out.println("ThreeIndexSwap Data 1 I/P: " + Arrays.toString(array) + ", O/P: " + Arrays.toString(reArrangeArrayThreeIndexSwap(array)));

        array = new int[]{-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        System.out.println("ThreeIndexSwap Data 2 I/P: " + Arrays.toString(array) + ", O/P: " + Arrays.toString(reArrangeArrayThreeIndexSwap(array)));

        array = new int[]{-1, 2, -3, 4, 5, 6, -7, 8, 9};
        System.out.println("DutchFlagMod Data 1 I/P: " + Arrays.toString(array) + ", O/P: " + Arrays.toString(reArrangeArrayDutchFlagMod(array)));

        array = new int[]{-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        System.out.println("DutchFlagMod Data 2 I/P: " + Arrays.toString(array) + ", O/P: " + Arrays.toString(reArrangeArrayDutchFlagMod(array)));
    }
}

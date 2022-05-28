package practice.ds.array;

public class Problem5 {

    /**
     * Find the Rotation Count in Rotated Sorted array
     * https://www.geeksforgeeks.org/find-rotation-count-rotated-sorted-array/
     */

    public static int findRotationCount(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) return startIndex % (array.length - 1);

        int midIndex = startIndex + (endIndex - startIndex)/2;

        if (array[midIndex] > array[midIndex + 1]) return midIndex + 1;
        if (array[midIndex] < array[midIndex - 1]) return midIndex;

        if (array[midIndex] >= array[startIndex]) {
            return findRotationCount(array, midIndex + 1, endIndex);
        } else {
            return findRotationCount(array, startIndex, midIndex - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("1 --> " + findRotationCount(new int[]{15, 18, 2, 3, 6, 12}, 0, 5));
        System.out.println("2 --> " + findRotationCount(new int[]{7, 9, 11, 12, 5}, 0, 4));
        System.out.println("3 --> " + findRotationCount(new int[]{7, 9, 11, 12, 15}, 0, 4));
    }
}

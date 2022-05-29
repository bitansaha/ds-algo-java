package practice.ds.array;

public class Problem8 {

    /**
     * Print left rotation of array in O(n) time and O(1) space
     * https://www.geeksforgeeks.org/print-left-rotation-array/
     */

    public static void printRotatedArray(int[] array, int rotationCount) {
        rotationCount = rotationCount % array.length;
        for (int printIndex = rotationCount, countIndex = 0; countIndex < array.length; countIndex++, printIndex = (printIndex + 1) % array.length) {
            if (countIndex == 0)
                System.out.print(array[printIndex]);
            else
                System.out.print(", " + array[printIndex]);

            if (countIndex == (array.length - 1)) System.out.println("");
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 5, 7, 9};
        printRotatedArray(array, 1);
        printRotatedArray(array, 3);
        printRotatedArray(array, 4);
        printRotatedArray(array, 6);
    }
}

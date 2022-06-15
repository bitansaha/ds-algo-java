package practice.ds.array;

public class Problem10 {

    /**
     * Count of possible rotations of given Array to remove largest element from first half
     * https://www.geeksforgeeks.org/count-of-possible-rotations-of-given-array-to-remove-largest-element-from-first-half/
     */

    public static int findNumberOfCyclicShifts(int[] array) {
        Integer maxValue = null;
        int initialMaxValueIndex = -1;
        int maxMaxValueIndex = -1;

        for (int index = 0; index < array.length; index++) {
            if (maxValue == null) {
                maxValue = array[index];
                maxMaxValueIndex = initialMaxValueIndex = index;
            } else {
                int currentValue = array[index];
                if (currentValue == maxValue) {
                    maxMaxValueIndex = index;
                } else if (currentValue > maxValue) {
                    maxValue = currentValue;
                    maxMaxValueIndex = initialMaxValueIndex = index;
                }
            }
        }

        if (maxValue != null
                && ((maxMaxValueIndex - initialMaxValueIndex) < array.length/2)
                && (initialMaxValueIndex < array.length/2)) {
            int leftMinRotation = array.length/2 - initialMaxValueIndex;
            int rightMaxRotation = array.length - 1 - maxMaxValueIndex;
            return Math.max(leftMinRotation, rightMaxRotation);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 3, 5, 3, 3, 3};
        System.out.println(findNumberOfCyclicShifts(array));
    }
}

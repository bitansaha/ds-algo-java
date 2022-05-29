package practice.ds.array;

public class Problem9 {

    /**
     * Find element at given index after a number of rotations
     * https://www.geeksforgeeks.org/find-element-given-index-number-rotations/
     */
    public static int getMultiRotatedIndex(int[] array, int[][] ranges, int rotationCount, int index) {
        for (int rangeIndex = ranges.length - 1; rangeIndex >= 0; rangeIndex--) {
            int rangeStartIndex = ranges[rangeIndex][0];
            int rangeEndIndex = ranges[rangeIndex][1];

            if (index >= rangeStartIndex && index <= rangeEndIndex) {
                for (int currentRotationCount = 1; currentRotationCount <= rotationCount; currentRotationCount++) {
                    index--;
                    if (index < rangeStartIndex) index = rangeEndIndex;
                }
            }
        }

        return array[index];
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        int[][] ranges = new int[][]{
                {0, 2},
                {0, 3}
        };
        int rotationCount = 1;
        int index = 1;
        System.out.println("1 --> " + getMultiRotatedIndex(array, ranges, rotationCount, index));

        array = new int[]{10, 20, 30, 40, 50};
        ranges = new int[][]{
                {0, 2},
                {1, 4},
                {0, 3}
        };
        rotationCount = 1;
        index = 1;
        System.out.println("2 --> " + getMultiRotatedIndex(array, ranges, rotationCount, index));

        array = new int[]{1, 2, 3, 4, 5};
        ranges = new int[][]{
                {0, 3},
                {3, 4},
                {0, 4},
                {0, 2}
        };
        rotationCount = 3;
        index = 2;
        System.out.println("3 --> " + getMultiRotatedIndex(array, ranges, rotationCount, index));
    }
}

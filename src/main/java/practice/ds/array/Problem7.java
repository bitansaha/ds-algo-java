package practice.ds.array;

public class Problem7 {

    /**
     * Queries on Left and Right Circular shift on array
     * https://www.geeksforgeeks.org/queries-left-right-circular-shift-array/
     */

    public static int leftRotate(int currentStartIndex, int arrayLength, int rotateCount) {
        return (currentStartIndex + rotateCount) % arrayLength;
    }

    public static int rightRotate(int currentStartIndex, int arrayLength, int rotationCount) {
        int rightRotateIndex =  (currentStartIndex - rotationCount) % arrayLength;
        return rightRotateIndex < 0 ? arrayLength + rightRotateIndex : rightRotateIndex;
    }

    public static int cumulativeSumByIndexRange(int[] cumulativeSumArray,
                                                int currentStartIndex,
                                                int arrayLength,
                                                int startRangeIndex,
                                                int endRangeIndex) {
        int adjustedStartRangeIndex = (currentStartIndex + startRangeIndex) % arrayLength;
        int adjustedEndRangeIndex = (currentStartIndex + endRangeIndex) % arrayLength;

        if (adjustedStartRangeIndex <= adjustedEndRangeIndex) {
            return cumulativeSumArray[adjustedEndRangeIndex + 1] - cumulativeSumArray[adjustedStartRangeIndex];
        } else {
            return cumulativeSumArray[adjustedEndRangeIndex + 1] + cumulativeSumArray[arrayLength] - cumulativeSumArray[adjustedStartRangeIndex];
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        int[] cumulativeSumArray = new int[array.length + 1];

        cumulativeSumArray[0] = 0;

        for (int cumulativeSumArrayIndex = 1; cumulativeSumArrayIndex < cumulativeSumArray.length; cumulativeSumArrayIndex++) {
            cumulativeSumArray[cumulativeSumArrayIndex] = cumulativeSumArray[cumulativeSumArrayIndex - 1] + array[cumulativeSumArrayIndex - 1];
        }

        int currentStartIndex = 0;

        currentStartIndex = rightRotate(currentStartIndex, array.length, 3);
        System.out.println("1 --> " + cumulativeSumByIndexRange(cumulativeSumArray, currentStartIndex, array.length, 0, 2));
        currentStartIndex = leftRotate(currentStartIndex, array.length, 1);
        System.out.println("2 --> " + cumulativeSumByIndexRange(cumulativeSumArray, currentStartIndex, array.length, 1, 4));

    }
}

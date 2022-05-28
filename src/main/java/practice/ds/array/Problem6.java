package practice.ds.array;

public class Problem6 {

    /**
     * Find a rotation with maximum hamming distance
     * https://www.geeksforgeeks.org/find-a-rotation-with-maximum-hamming-distance/
     */

    public static int maxHummingRotation(int[] array) {
        int maxHummingRotation = 0;

        for (int rotationCount = 1; rotationCount < array.length; rotationCount++) {
            int rotationHummingValue = 0;
            for (int rotationIndex = rotationCount, normalIndex = 0; normalIndex < array.length; normalIndex++, rotationIndex = (rotationIndex + 1) % array.length) {
                if (array[rotationIndex] != array[normalIndex]) rotationHummingValue++;
            }

            maxHummingRotation = Math.max(maxHummingRotation, rotationHummingValue);
        }

        return maxHummingRotation;
    }

    public static void main(String[] args) {
        System.out.println("1 --> " + maxHummingRotation(new int[]{1, 4, 1}));
        System.out.println("4 --> " + maxHummingRotation(new int[]{2, 4, 8, 0}));
    }
}

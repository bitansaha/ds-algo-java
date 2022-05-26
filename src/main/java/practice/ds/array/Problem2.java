package practice.ds.array;

import java.util.Optional;

public class Problem2 {

    public static Optional<Integer> searchSortedRotatedArray(Integer[] sortedRotatedArray, int searchValue) {
        int rotationIndex = findRotationIndex(sortedRotatedArray, 0, sortedRotatedArray.length - 1);

        Optional<Integer> searchIndexLeft = binarySearch(sortedRotatedArray, searchValue, 0, rotationIndex);
        Optional<Integer> searchIndexRight = binarySearch(sortedRotatedArray, searchValue, rotationIndex + 1, sortedRotatedArray.length - 1);

        if (searchIndexLeft.isPresent())
            return searchIndexLeft;
        else
            return searchIndexRight;

    }

    public static Optional<Integer> binarySearch(Integer[] sortedRotatedArray, int searchValue, int startIndex, int endIndex) {

        if (startIndex > endIndex) return Optional.empty();

        int midIndex = startIndex + (endIndex - startIndex)/2;

        if (sortedRotatedArray[midIndex] == searchValue) return Optional.of(midIndex);

        if (sortedRotatedArray[midIndex] > searchValue) {
            return binarySearch(sortedRotatedArray, searchValue, startIndex, midIndex - 1);
        } else {
            return binarySearch(sortedRotatedArray, searchValue, midIndex + 1, endIndex);
        }
    }

    public static int findRotationIndex(Integer[] sortedRotatedArray, int startIndex, int endIndex) {

        if (startIndex >= endIndex) return startIndex;

        int midIndex = startIndex + (endIndex - startIndex)/2;

        if (sortedRotatedArray[midIndex] > sortedRotatedArray[midIndex + 1]) return midIndex;
        if (sortedRotatedArray[midIndex] < sortedRotatedArray[midIndex - 1]) return midIndex - 1;

        if (sortedRotatedArray[midIndex] > sortedRotatedArray[startIndex]) {
            return findRotationIndex(sortedRotatedArray, midIndex + 1, endIndex);
        } else {
            return findRotationIndex(sortedRotatedArray, startIndex, midIndex - 1);
        }
    }

    public static void main (String[] args) {
        searchSortedRotatedArray(new Integer[]{5, 6, 7, 8, 9, 10, 1, 2, 3}, 3)
                .ifPresent(index -> System.out.println("1 --> " + index));

        searchSortedRotatedArray(new Integer[]{5, 6, 7, 8, 9, 10, 1, 2, 3}, 30)
                .ifPresent(index -> System.out.println("2 --> " + index));

        searchSortedRotatedArray(new Integer[]{30, 40, 50, 10, 20}, 10)
                .ifPresent(index -> System.out.println("3 --> " + index));
    }
}

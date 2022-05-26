package practice.ds.array;

import org.apache.commons.math3.util.Pair;

import java.util.Optional;

public class Problem3 {

    public static Optional<Pair<Integer, Integer>> findSumPairInSortedRotatedArray(Integer[] sortedRotatedArray, int sumValue) {
        if (sortedRotatedArray.length == 1) return Optional.empty();

        int rotationIndex = findRotationIndex(sortedRotatedArray, 0, sortedRotatedArray.length - 1);

        int firstIndex = rotationIndex;
        int secondIndex = rotationIndex + 1;

        while (true) {
            if (firstIndex > rotationIndex && secondIndex > rotationIndex && secondIndex >= firstIndex) return Optional.empty();
            if (firstIndex <= rotationIndex && secondIndex <= rotationIndex && secondIndex >= firstIndex) return Optional.empty();
            if (firstIndex > rotationIndex && secondIndex <= rotationIndex) return Optional.empty();

            if ((sortedRotatedArray[firstIndex] + sortedRotatedArray[secondIndex]) == sumValue)
                return Optional.of(Pair.create(sortedRotatedArray[secondIndex], sortedRotatedArray[firstIndex]));

            if ((sortedRotatedArray[firstIndex] + sortedRotatedArray[secondIndex]) > sumValue) {
                // move the first index
                firstIndex = sortedRotatedArray.length - ((sortedRotatedArray.length - firstIndex) % sortedRotatedArray.length) - 1;
            } else {
                // move the second index
                secondIndex = (secondIndex + 1) % sortedRotatedArray.length;
            }


        }
    }

    public static Integer findRotationIndex(Integer[] sortedRotatedArray, Integer startIndex, Integer endIndex) {
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
        findSumPairInSortedRotatedArray(new Integer[]{11, 15, 6, 8, 9, 10}, 16)
                .ifPresent(pair -> System.out.println("1 --> " + pair.getFirst() + ", " + pair.getSecond()));

        findSumPairInSortedRotatedArray(new Integer[]{11, 15, 26, 38, 9, 10}, 35)
                .ifPresent(pair -> System.out.println("2 --> " + pair.getFirst() + ", " + pair.getSecond()));

        findSumPairInSortedRotatedArray(new Integer[]{11, 15, 26, 38, 9, 10}, 45)
                .ifPresent(pair -> System.out.println("3 --> " + pair.getFirst() + ", " + pair.getSecond()));
    }
}

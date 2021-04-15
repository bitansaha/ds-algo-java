package practice.ds.array;

import java.util.Arrays;

public class Problem1 {

  // Array Rotation
  // https://www.geeksforgeeks.org/array-rotation/
  // https://www.geeksforgeeks.org/program-for-array-rotation-continued-reversal-algorithm/

  interface ArrayRotation {
    public int[] leftRotate (int[] array, int rotationCount);

    public int[] rightRotate (int[] array, int rotationCount);
  }

  // Brut Force
  // Time Complexity - O(n^2)
  // Space Complexity - O(1)
  static class Solution1 implements ArrayRotation {

      public int[] leftRotate (int[] array, int rotationCount) {

        rotationCount = rotationCount % array.length;

        for (int currentRotationIndex = 0; currentRotationIndex < rotationCount; currentRotationIndex++) {
          int sourceValue = array[array.length - 1];

          for (int sourceIndex = array.length - 1; sourceIndex > 0; sourceIndex--) {
            int destinationIndex = sourceIndex - 1;
            int temp = array[destinationIndex];
            array[destinationIndex] = sourceValue;
            sourceValue = temp;
          }

          array[array.length - 1] = sourceValue;
        }

        return array;
      }

      public int[] rightRotate (int[] array, int rotationCount) {

        rotationCount = rotationCount % array.length;

        for (int currentRotationIndex = 0; currentRotationIndex < rotationCount; currentRotationIndex++) {
          int sourceValue = array[0];

          for (int sourceIndex = 0; sourceIndex < array.length - 1; sourceIndex++) {
            int destinationIndex = sourceIndex + 1;
            int temp = array[destinationIndex];
            array[destinationIndex] = sourceValue;
            sourceValue = temp;
          }

          array[0] = sourceValue;
        }

        return array;

      }
  }

  // Using extra memory
  // Time Complexity - O(n)
  // Space Complexity - O(n)
  static class Solution2 implements ArrayRotation {

    public int[] leftRotate (int[] array, int rotationCount) {

      rotationCount = rotationCount % array.length;

      int[] tempArray = new int[rotationCount];

      for (int sourceIndex = 0; sourceIndex < rotationCount; sourceIndex++) {
        int destinationIndex = sourceIndex;
        tempArray[destinationIndex] = array[sourceIndex];
      }

      for (int sourceIndex = rotationCount; sourceIndex < array.length; sourceIndex++) {
        int destinationIndex = sourceIndex - rotationCount;
        array[destinationIndex] = array[sourceIndex];
      }

      for (int sourceIndex = 0; sourceIndex < rotationCount; sourceIndex++) {
        int destinationIndex = array.length - rotationCount + sourceIndex;
        array[destinationIndex] = tempArray[sourceIndex];
      }

      return array;

    }

    public int[] rightRotate (int[] array, int rotationCount) {

      rotationCount = rotationCount % array.length;

      int[] tempArray = new int[rotationCount];

      for (int sourceIndex = array.length - 1; sourceIndex > array.length - 1 - rotationCount; sourceIndex--) {
        int destinationIndex = array.length - 1 - sourceIndex;
        tempArray[destinationIndex] = array[sourceIndex];
      }

      for (int sourceIndex = array.length - 1 - rotationCount; sourceIndex >= 0; sourceIndex--) {
        int destinationIndex = sourceIndex + rotationCount;
        array[destinationIndex] = array[sourceIndex];
      }

      for (int sourceIndex = 0; sourceIndex < rotationCount; sourceIndex++) {
        int destinationIndex = rotationCount - 1 - sourceIndex;
        array[destinationIndex] = tempArray[sourceIndex];
      }

      return array;

    }
  }

  // Using Reverse methodology - L|R - LRev|RRev - (LRev|RRev)Rev
  // Time Complexity - O(n)
  // Space Complexity - O(1)
  static class Solution3 implements ArrayRotation {

    private void reverse (int[] array, int startIndex, int endIndex) {
        for (; startIndex < endIndex; startIndex++, endIndex--) {
          int temp = array[startIndex];
          array[startIndex] = array[endIndex];
          array[endIndex] = temp;
        }
    }

    public int[] leftRotate (int[] array, int rotationCount) {
      rotationCount = rotationCount % array.length;

      int leftStartIndex = 0;
      int leftEndIndex = rotationCount - 1;
      reverse(array, leftStartIndex, leftEndIndex);

      int rightStartIndex = rotationCount;
      int rightEndIndex = array.length - 1;
      reverse(array, rightStartIndex, rightEndIndex);

      reverse(array, leftStartIndex, rightEndIndex);

      return array;
    }

    public int[] rightRotate (int[] array, int rotationCount) {
      rotationCount = rotationCount % array.length;

      int leftStartIndex = 0;
      int leftEndIndex = array.length - 1 - rotationCount;
      reverse(array, leftStartIndex, leftEndIndex);

      int rightStartIndex = array.length - rotationCount;
      int rightEndIndex = array.length - 1;
      reverse(array, rightStartIndex, rightEndIndex);

      reverse(array, leftStartIndex, rightEndIndex);

      return array;
    }
  }

  // Using GCD groups
  // Time Complexity - O(n)
  // Space Complexity - O(1)
  static class Solution4 implements ArrayRotation {

    private int calculateGCD(int rotationCount, int size) {
      if (size == 0) return rotationCount;
      return calculateGCD(size, rotationCount % size);
    }

    public int[] leftRotate (int[] array, int rotationCount) {
      rotationCount = rotationCount % array.length;

      int gcd = calculateGCD(rotationCount, array.length);

      for (int gcdIndex = 0; gcdIndex < gcd; gcdIndex++) {
        int sourceIndex = gcdIndex;
        int sourceValue = array[sourceIndex];

        while (true) {
          int destinationIndex = sourceIndex - rotationCount;
          if (destinationIndex < 0) {
            destinationIndex = destinationIndex + array.length;
          }

          if (destinationIndex == gcdIndex) break;

          int temp = array[destinationIndex];
          array[destinationIndex] = sourceValue;

          sourceIndex = destinationIndex;
          sourceValue = temp;
        }

        array[gcdIndex] = sourceValue;
      }

      return array;
    }

    public int[] rightRotate (int[] array, int rotationCount) {
      rotationCount = rotationCount % array.length;

      int gcd = calculateGCD(rotationCount, array.length);

      for (int gcdIndex = array.length - 1; gcdIndex > array.length - 1 - gcd; gcdIndex--) {
        int sourceIndex = gcdIndex;
        int sourceValue = array[sourceIndex];

        while (true) {
          int destinationIndex = (sourceIndex + rotationCount) % array.length;

          if (destinationIndex == gcdIndex) break;

          int temp = array[destinationIndex];
          array[destinationIndex] = sourceValue;

          sourceIndex = destinationIndex;
          sourceValue = temp;
        }

        array[gcdIndex] = sourceValue;
      }

      return array;
    }
  }

  public static void main(String[] args) {
    Solution1 solution1 = new Solution1();
    Solution2 solution2 = new Solution2();
    Solution3 solution3 = new Solution3();
    Solution4 solution4 = new Solution4();

    int[] originalArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    int rotationCount = 3;

    int[] array = new int[originalArray.length];
    System.arraycopy(originalArray, 0, array, 0, originalArray.length);
    int[] solution1LeftRotateResponse = solution1.leftRotate(array, rotationCount);

    array = new int[originalArray.length];
    System.arraycopy(originalArray, 0, array, 0, originalArray.length);
    int[] solution1RightRotateResponse = solution1.rightRotate(array, rotationCount);

    array = new int[originalArray.length];
    System.arraycopy(originalArray, 0, array, 0, originalArray.length);
    int[] solution2LeftRotateResponse = solution2.leftRotate(array, rotationCount);

    array = new int[originalArray.length];
    System.arraycopy(originalArray, 0, array, 0, originalArray.length);
    int[] solution2RightRotateResponse = solution2.rightRotate(array, rotationCount);

    array = new int[originalArray.length];
    System.arraycopy(originalArray, 0, array, 0, originalArray.length);
    int[] solution3LeftRotateResponse = solution3.leftRotate(array, rotationCount);

    array = new int[originalArray.length];
    System.arraycopy(originalArray, 0, array, 0, originalArray.length);
    int[] solution3RightRotateResponse = solution3.rightRotate(array, rotationCount);

    array = new int[originalArray.length];
    System.arraycopy(originalArray, 0, array, 0, originalArray.length);
    int[] solution4LeftRotateResponse = solution4.leftRotate(array, rotationCount);

    array = new int[originalArray.length];
    System.arraycopy(originalArray, 0, array, 0, originalArray.length);
    int[] solution4RightRotateResponse = solution4.rightRotate(array, rotationCount);

    System.out.println("Left Rotate - Extra Memory: " + Arrays.equals(solution1LeftRotateResponse, solution2LeftRotateResponse));
    System.out.println("Right Rotate - Extra Memory: " + Arrays.equals(solution1RightRotateResponse, solution2RightRotateResponse));

    System.out.println("Left Rotate - Reverse: " + Arrays.equals(solution1LeftRotateResponse, solution3LeftRotateResponse));
    System.out.println("Right Rotate - Reverse: " + Arrays.equals(solution1RightRotateResponse, solution3RightRotateResponse));

    System.out.println("Left Rotate - GCD: " + Arrays.equals(solution1LeftRotateResponse, solution4LeftRotateResponse));
    System.out.println("Right Rotate - GCD: " + Arrays.equals(solution1RightRotateResponse, solution4RightRotateResponse));

  }
}

package practice.ds.matrix.medium;

import java.util.BitSet;
// Inplace (Fixed space) M x N size matrix transpose
// https://www.geeksforgeeks.org/inplace-m-x-n-size-matrix-transpose/
public class Problem12 {

    public static void print2DMatrix(int[] arrayMatrix, int rowLength, int colLength) {
        for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
            for (int colIndex = 0; colIndex < colLength; colIndex++) {
                System.out.print(arrayMatrix[(rowIndex * colLength) + colIndex] + " ");
            }
            System.out.print("\n");
        }
    }

    // Time-Complexity :: O(m*n)
    // Space-Complexity :: O(m*n bits)
    public static int[] transposeMatrix(int[] arrayMatrix, int orLength, int ocLength) {
        BitSet indexBitSet = new BitSet(orLength * ocLength);

        int cyclicIndex = 0;

        while ((cyclicIndex = indexBitSet.nextClearBit(cyclicIndex + 1)) < orLength * ocLength) {
            int ol = cyclicIndex;
            int olValue = arrayMatrix[ol];
            while (true) {
                int or = ol / ocLength;
                int oc = ol % ocLength;
                int nl = (oc * orLength) + or;
                int temp = arrayMatrix[nl];
                arrayMatrix[nl] = olValue;
                ol = nl;
                olValue = temp;
                indexBitSet.set(nl);

                if (nl == cyclicIndex) break;
            }
        }

        return arrayMatrix;
    }

    public static void main(String[] args) {
        int r = 2, c = 3;
        int size = r * c;
        int[] arrayMatrix = new int[size];

        for (int i = 0; i < size; i++)
            arrayMatrix[i] = i + 1;

        System.out.println(" ========== Original Matrix =========== ");
        print2DMatrix(arrayMatrix, r, c);
        arrayMatrix = transposeMatrix(arrayMatrix, r, c);
        System.out.println(" ========== Transposed Matrix =========== ");
        print2DMatrix(arrayMatrix, c, r);
    }
}

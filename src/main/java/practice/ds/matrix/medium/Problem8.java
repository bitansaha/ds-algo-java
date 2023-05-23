package practice.ds.matrix.medium;
// Queries in a Matrix
// https://www.geeksforgeeks.org/queries-in-a-matrix/
public class Problem8 {

    // Time-Complexity :: O(1)
    // Space-Complexity :: O(m + n)
    // Auxiliary Space-Complexity :: O(m + n)
    public static class Matrix {
        private final int m;
        private final int n;

        private final int[] mLocations;
        private final int[] nLocations;

        public Matrix (int m, int n) {
            this.m = m;
            this.n = n;
            this.mLocations = new int[this.m];
            this.nLocations = new int[this.n];

            for (int mIndex = 0; mIndex < this.m; mIndex++) mLocations[mIndex] = mIndex;
            for (int nIndex = 0; nIndex < this.n; nIndex++) nLocations[nIndex] = nIndex;
        }

        public void actionR(int rowIndexOne, int rowIndexTwo) {
            int temp = mLocations[rowIndexOne - 1];
            mLocations[rowIndexOne - 1] = mLocations[rowIndexTwo - 1];
            mLocations[rowIndexTwo - 1] = temp;
        }

        public void actionC(int colIndexOne, int colIndexTwo) {
            int temp = nLocations[colIndexOne - 1];
            nLocations[colIndexOne - 1] = nLocations[colIndexTwo - 1];
            nLocations[colIndexTwo - 1] = temp;
        }

        public void actionP(int rowIndex, int colIndex) {
            int originalRow = mLocations[rowIndex - 1];
            int originalCol = nLocations[colIndex - 1];

            int value = 0;

            if ((originalRow - 1) >= 0) {
                value += originalRow * n;
            }

            value += originalCol;

            value += 1;

            System.out.println("Value at (" + rowIndex +", " + colIndex + ") is: " + value);
        }
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix(1234, 5678);
        matrix.actionR(1, 2);
        matrix.actionP(1, 1);
        matrix.actionP(2, 1);
        matrix.actionC(1, 2);
        matrix.actionP(1, 1);
        matrix.actionP(2, 1);
    }
}

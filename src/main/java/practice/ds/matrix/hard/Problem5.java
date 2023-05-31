package practice.ds.matrix.hard;

// Construct Ancestor Matrix from a Given Binary Tree
// https://www.geeksforgeeks.org/construct-ancestor-matrix-from-a-given-binary-tree/
public class Problem5 {

    // Time-Complexity :: O(n^2)
    // Space-Complexity :: O(n^2)
    public static void fillMatrixByNode(Node node, int[][] ancestorMatrix, int matrixSize) {
        if (node == null) return;
        fillMatrixByNode(node.left, ancestorMatrix, matrixSize);
        fillMatrixByNode(node.right, ancestorMatrix, matrixSize);

        if (node.left != null) {
            for (int colIndex = 0; colIndex < matrixSize; colIndex++) {
                if (ancestorMatrix[node.left.data][colIndex] == 1) {
                    ancestorMatrix[node.data][colIndex] = 1;
                }
            }
            ancestorMatrix[node.data][node.left.data] = 1;
        }

        if (node.right != null) {
            for (int colIndex = 0; colIndex < matrixSize; colIndex++) {
                if (ancestorMatrix[node.right.data][colIndex] == 1) {
                    ancestorMatrix[node.data][colIndex] = 1;
                }
            }
            ancestorMatrix[node.data][node.right.data] = 1;
        }
    }

    public static int[][] constructAncestorMatrix(Node root, int matrixSize) {
        int[][] ancestorMatrix = new int[matrixSize][matrixSize];
        fillMatrixByNode(root, ancestorMatrix, matrixSize);
        return ancestorMatrix;
    }

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix.length >= 1 ? matrix[0].length - 1 : 0;

        for (int rowIndex = 0; rowIndex <= rows; rowIndex++) {
            for (int colIndex = 0; colIndex <= cols; colIndex++) {
                System.out.print(matrix[rowIndex][colIndex] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Node tree_root = new Node(5);
        tree_root.left = new Node (1);
        tree_root.right = new Node(2);
        tree_root.left.left = new Node(0);
        tree_root.left.right = new Node(4);
        tree_root.right.left = new Node(3);

        int[][] ancestorMatrix = constructAncestorMatrix(tree_root, 6);

        printMatrix(ancestorMatrix);
    }

    static class Node
    {
        public int data ;
        public Node left ,right;
        public Node (int data)
        {
            this.data = data;
            this.left = this.right = null;
        }
    }
}

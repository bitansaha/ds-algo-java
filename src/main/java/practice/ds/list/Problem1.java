package practice.ds.list;

public class Problem1 {

    /**
     * Left rotate Linked List by X in groups of Y nodes
     * https://www.geeksforgeeks.org/left-rotate-linked-list-by-x-in-groups-of-y-nodes/
     */

    public static abstract class SinglyLinkedList<DataType> {
        class Node<NodeDataType> {
            private Node(NodeDataType data, Node<NodeDataType> nextNode) {
                this.value = data;
                this.next = nextNode;
            }

            private Node(NodeDataType data) {
                this(data, null);
            }

            NodeDataType value;
            Node<NodeDataType> next;
        }

        protected Node<DataType> head;

        public void addAll(DataType[] dataArray) {
            for (DataType dataType : dataArray) {
                this.add(dataType);
            }
        }

        public void add(DataType data) {
            head = new Node<DataType>(data, head);
        }

        public void printList() {
            Node<DataType> currentNode = head;
            while(currentNode != null) {
                System.out.print(currentNode.value);
                if (currentNode.next != null) {
                    System.out.print(", ");
                } else {
                    System.out.println("");
                }
                currentNode = currentNode.next;
            }
        }

        public abstract void batchRotate(int rotationCount, int groupSize);

    }

    public static class SinglyLinkedListOne<DataType> extends SinglyLinkedList<DataType> {

        @Override
        public void batchRotate(int rotationCount, int groupSize) {
            rotationCount = rotationCount % groupSize;
            Node<DataType> headNode = null;
            Node<DataType> lastGroupTailNode = null;
            Node<DataType> nextGroupHeadNode = head;

            while (nextGroupHeadNode != null) {
                RotatedListGroup rotatedListGroup = rotateGroup(nextGroupHeadNode, rotationCount, groupSize);
                if (headNode == null) headNode = rotatedListGroup.groupHeadNode;
                if (lastGroupTailNode != null) lastGroupTailNode.next = rotatedListGroup.groupHeadNode;
                lastGroupTailNode = rotatedListGroup.groupTailNode;
                nextGroupHeadNode = rotatedListGroup.nextGroupHeadNode;
            }

            head = headNode;
        }

        private RotatedListGroup rotateGroup(Node<DataType> groupHeadNode, int rotationCount, int groupSize) {
            if (rotationCount == 0) {
                Node<DataType> groupTailNode = groupHeadNode;
                for (int groupIndex = 2; groupIndex <= groupSize; groupIndex++) {
                    if (groupTailNode.next == null) return rotateGroup(groupHeadNode, rotationCount % (groupIndex - 1), groupIndex - 1);
                    groupTailNode = groupTailNode.next;
                }
                return new RotatedListGroup (
                        groupHeadNode,
                        groupTailNode,
                        groupTailNode.next
                );
            }

            int leftGroupSize = groupSize - rotationCount;
            int rightGroupSize = rotationCount;

            Node<DataType> leftGroupHeadNode = groupHeadNode;
            Node<DataType> leftGroupTailNode = groupHeadNode;

            for (int leftGroupIndex = 2; leftGroupIndex <= leftGroupSize; leftGroupIndex++) {
                if (leftGroupTailNode.next == null) return rotateGroup(groupHeadNode, rotationCount % (leftGroupIndex - 1), leftGroupIndex - 1);
                leftGroupTailNode = leftGroupTailNode.next;
            }

            if (leftGroupTailNode.next == null) return rotateGroup(groupHeadNode, rotationCount % leftGroupSize, leftGroupSize);

            Node<DataType> rightGroupHeadNode = leftGroupTailNode.next;
            Node<DataType> rightGroupTailNode = leftGroupTailNode.next;

            for (int rightGroupIndex = 2; rightGroupIndex <= rightGroupSize; rightGroupIndex++) {
                if (rightGroupTailNode.next == null) return rotateGroup(groupHeadNode, rotationCount % (leftGroupSize + rightGroupIndex - 1), leftGroupSize + rightGroupIndex - 1);
                rightGroupTailNode = rightGroupTailNode.next;
            }

            Node<DataType> nextGroupHeadNode = rightGroupTailNode.next;

            rightGroupTailNode.next = leftGroupHeadNode;
            leftGroupTailNode.next = null;

            return new RotatedListGroup (
                    rightGroupHeadNode,
                    leftGroupTailNode,
                    nextGroupHeadNode
            );

        }

        private class RotatedListGroup {
            Node<DataType> groupHeadNode;
            Node<DataType> groupTailNode;
            Node<DataType> nextGroupHeadNode;

            public RotatedListGroup(Node<DataType> groupHeadNode,
                                    Node<DataType> groupTailNode,
                                    Node<DataType> nextGroupHeadNode) {
                this.groupHeadNode = groupHeadNode;
                this.groupTailNode = groupTailNode;
                this.nextGroupHeadNode = nextGroupHeadNode;
            }
        }
    }
    public static class SinglyLinkedListTwo<DataType> extends SinglyLinkedList<DataType> {

        @Override
        public void batchRotate(int rotationCount, int groupSize) {
            rotationCount = rotationCount % groupSize;
            int leftGroupSize = groupSize - rotationCount;
            int rightGroupSize = rotationCount;
            Node<DataType> headNode = null;
            Node<DataType> lastGroupTail = null;
            Node<DataType> nextGroupHead = head;

            while (nextGroupHead != null) {
                ReverseListGroupOP leftReverse = reverse(nextGroupHead, leftGroupSize);
                if (headNode == null) headNode = leftReverse.headNode;
                if (lastGroupTail != null) lastGroupTail.next = leftReverse.headNode;
                lastGroupTail = leftReverse.tailNode;
                nextGroupHead = leftReverse.nextGroupHeadNode;

                if (nextGroupHead != null) {
                    ReverseListGroupOP rightReverse = reverse(leftReverse.nextGroupHeadNode, rightGroupSize);
                    lastGroupTail.next = rightReverse.headNode;
                    lastGroupTail = rightReverse.tailNode;
                    nextGroupHead = rightReverse.nextGroupHeadNode;
                }
            }

            nextGroupHead = headNode;
            headNode = null;
            lastGroupTail = null;

            while (nextGroupHead != null) {
                ReverseListGroupOP reverse = reverse(nextGroupHead, groupSize);
                if (headNode == null) headNode = reverse.headNode;
                if (lastGroupTail != null) lastGroupTail.next = reverse.headNode;
                lastGroupTail = reverse.tailNode;
                nextGroupHead = reverse.nextGroupHeadNode;
            }

            head = headNode;
        }

        private ReverseListGroupOP reverse(Node<DataType> headNode, int nodeCount) {
            Node<DataType> tailNode = headNode;
            Node<DataType> nextGroupHeadNode = headNode.next;
            for (int nodeIndex = 2; nodeIndex <= nodeCount; nodeIndex++) {
                if (nextGroupHeadNode == null) break;

                Node<DataType> tempNode = nextGroupHeadNode.next;
                nextGroupHeadNode.next = headNode;
                headNode = nextGroupHeadNode;
                nextGroupHeadNode = tempNode;

            }

            tailNode.next = null;

            return new ReverseListGroupOP(
                    headNode,
                    tailNode,
                    nextGroupHeadNode
            );
        }

        private class ReverseListGroupOP {
            Node<DataType> headNode;
            Node<DataType> tailNode;
            Node<DataType> nextGroupHeadNode;
            private ReverseListGroupOP(Node<DataType> headNode, Node<DataType> tailNode, Node<DataType> nextGroupHeadNode) {
                this.headNode = headNode;
                this.tailNode = tailNode;
                this.nextGroupHeadNode = nextGroupHeadNode;
            }
        }
    }

    public static void main(String[] args) {
        // First Problem First Approach
        System.out.println(" ========= First Problem First Approach ========= ");
        SinglyLinkedList<Integer> singlyLinkedListOne = new SinglyLinkedListOne<>();

        for (int value = 100; value >= 10; value -= 10)
            singlyLinkedListOne.add(value);

        singlyLinkedListOne.printList();
        singlyLinkedListOne.batchRotate(2, 4);
        System.out.println("batch rotate ONE --> ");
        singlyLinkedListOne.printList();

        // First Problem Second Approach
        System.out.println(" ========= First Problem Second Approach ========= ");
        SinglyLinkedList<Integer> singlyLinkedListTwo = new SinglyLinkedListTwo<>();

        for (int value = 100; value >= 10; value -= 10)
            singlyLinkedListTwo.add(value);

        singlyLinkedListTwo.printList();
        singlyLinkedListTwo.batchRotate(2, 4);
        System.out.println("batch rotate TWO --> ");
        singlyLinkedListTwo.printList();

        // Second Problem First Approach
        System.out.println("\n ========= Second Problem First Approach ========= ");
        singlyLinkedListOne = new SinglyLinkedListOne<>();

        for (int value = 100; value >= 40; value -= 10)
            if (value != 50) singlyLinkedListOne.add(value);

        singlyLinkedListOne.printList();
        singlyLinkedListOne.batchRotate(1, 3);
        System.out.println("batch rotate ONE --> ");
        singlyLinkedListOne.printList();

        // Second Problem Second Approach
        System.out.println("\n ========= Second Problem Second Approach ========= ");
        singlyLinkedListTwo = new SinglyLinkedListTwo<>();

        for (int value = 100; value >= 40; value -= 10)
            if (value != 50) singlyLinkedListTwo.add(value);

        singlyLinkedListTwo.printList();
        singlyLinkedListTwo.batchRotate(1, 3);
        System.out.println("batch rotate TWO --> ");
        singlyLinkedListTwo.printList();
    }
}

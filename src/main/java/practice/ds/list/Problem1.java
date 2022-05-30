package practice.ds.list;

public class Problem1 {

    /**
     * Left rotate Linked List by X in groups of Y nodes
     * https://www.geeksforgeeks.org/left-rotate-linked-list-by-x-in-groups-of-y-nodes/
     */

    public static class SinglyLinkedList<DataType> {
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

        private Node<DataType> head;

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

        public void batchRotateOne(int rotationCount, int groupSize) {
            if (head == null) return;

            rotationCount = rotationCount % groupSize;

            int rightGroupSize = rotationCount;
            int leftGroupSize = groupSize - rightGroupSize;

            Node<DataType> finalHead = null;

            Node<DataType> groupLeftHead = head;
            Node<DataType> groupLeftTail = groupLeftHead;
            Node<DataType> groupRightHead = null;
            Node<DataType> groupRightTail = null;

            Node<DataType> lastGroupTail = null;
            Node<DataType> nextGroupHead = null;

            masterLoop:
            while (groupLeftHead != null) {
                if (leftGroupSize > 0) {
                    for (int leftGroupIndex = 1; leftGroupIndex < leftGroupSize; leftGroupIndex++) {
                        groupLeftTail = groupLeftTail.next;

                        if (groupLeftTail == null) {
                            int lastGroupSize = leftGroupIndex;
                            rightGroupSize = rotationCount % lastGroupSize == 0 ? lastGroupSize : rotationCount % lastGroupSize;
                            leftGroupSize = lastGroupSize - rightGroupSize;
                            groupLeftTail = groupLeftHead;
                            continue masterLoop;
                        }
                    }

                    groupRightHead = groupLeftTail.next;
                    groupRightTail = groupRightHead;
                    groupLeftTail.next = null;
                } else {
                    groupRightHead = groupLeftHead;
                    groupRightTail = groupRightHead;
                    groupLeftHead = null;
                }

                if (groupRightHead == null) {
                    int lastGroupSize = leftGroupSize;
                    rightGroupSize = rotationCount % lastGroupSize == 0 ? lastGroupSize : rotationCount % lastGroupSize;
                    leftGroupSize = lastGroupSize - rightGroupSize;
                    groupLeftTail = groupLeftHead;
                    groupRightHead = null;
                    continue masterLoop;
                }

                for (int rightGroupIndex = 1; rightGroupIndex < rightGroupSize; rightGroupIndex++) {
                    groupRightTail = groupRightHead.next;

                    if (groupRightTail == null) {
                        int lastGroupSize = leftGroupSize + rightGroupIndex;
                        rightGroupSize = rotationCount % lastGroupSize == 0 ? lastGroupSize : rotationCount % lastGroupSize;
                        leftGroupSize = lastGroupSize - rightGroupSize;
                        groupLeftTail = groupLeftHead;
                        groupRightHead = null;
                        continue masterLoop;
                    }
                }

                nextGroupHead = groupRightTail.next;
                groupRightTail.next = groupLeftHead;
                if (lastGroupTail != null ) lastGroupTail.next = groupRightHead;
                lastGroupTail = groupLeftTail;
                if (finalHead == null) finalHead = groupRightHead;

                groupLeftHead = nextGroupHead;
                groupLeftTail = groupLeftHead;
                groupRightHead = null;
                groupRightTail = null;

                nextGroupHead = null;
            }

            head = finalHead;
        }
    }

    public static void main(String[] args) {
        // First Problem First Approach
        System.out.println(" ========= First Problem First Approach ========= ");
        SinglyLinkedList<Integer> singlyLinkedListOne = new SinglyLinkedList<>();

        for (int value = 100; value >= 10; value -= 10)
            singlyLinkedListOne.add(value);

        singlyLinkedListOne.printList();
        singlyLinkedListOne.batchRotateOne(2, 4);
        System.out.println("batch rotate ONE --> ");
        singlyLinkedListOne.printList();

        // Second Problem First Approach
        System.out.println("\n ========= Second Problem First Approach ========= ");
        singlyLinkedListOne = new SinglyLinkedList<>();

        for (int value = 100; value >= 40; value -= 10)
            if (value != 50) singlyLinkedListOne.add(value);

        singlyLinkedListOne.printList();
        singlyLinkedListOne.batchRotateOne(1, 3);
        System.out.println("batch rotate ONE --> ");
        singlyLinkedListOne.printList();
    }
}

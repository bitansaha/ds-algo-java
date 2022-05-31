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

        public void batchRotateTwo(int rotationCount, int groupSize) {
            rotationCount = rotationCount % groupSize;

            int leftGroupSize = groupSize - rotationCount;
            int rightGroupSize = rotationCount;

            Node<DataType> futureHead = null;

            Node<DataType> leftGroupHead = head;
            Node<DataType> rightGroupHead = null;

            Node<DataType> lastGroupTail = null;

            while (leftGroupHead != null) {
                Node<DataType> currentNode = leftGroupHead.next;
                Node<DataType> leftGroupTail = leftGroupHead;

                for (int leftGroupIndex = 1; leftGroupIndex < leftGroupSize; leftGroupIndex++) {
                    if (currentNode == null) break;
                    Node<DataType> tempNode = currentNode.next;
                    currentNode.next = leftGroupHead;
                    leftGroupHead = currentNode;
                    currentNode = tempNode;
                }

                rightGroupHead = currentNode;
                if (rightGroupHead != null) currentNode = rightGroupHead.next;
                Node<DataType> rightGroupTail = rightGroupHead;

                for (int rightGroupIndex = 1; rightGroupIndex < rightGroupSize; rightGroupIndex++) {
                    if (currentNode == null) break;
                    Node<DataType> tempNode = currentNode.next;
                    currentNode.next = rightGroupHead;
                    rightGroupHead = currentNode;
                    currentNode = tempNode;
                }

                leftGroupTail.next = rightGroupHead;
                if (rightGroupTail != null) rightGroupTail.next = null;
                Node<DataType> nextGroupCurrentNode = currentNode;

                Node<DataType> headNode = leftGroupHead;
                Node<DataType> tailNode = leftGroupHead;
                currentNode = headNode.next;
                headNode.next = null;
                while (currentNode != null) {
                    Node<DataType> tempNode = currentNode.next;
                    currentNode.next = headNode;
                    headNode = currentNode;
                    currentNode = tempNode;
                }

                if (futureHead == null) futureHead = headNode;
                if (lastGroupTail != null) lastGroupTail.next = headNode;
                lastGroupTail = tailNode;

                leftGroupHead = nextGroupCurrentNode;
            }

            head = futureHead;
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

        // First Problem Second Approach
        System.out.println(" ========= First Problem Second Approach ========= ");
        singlyLinkedListOne = new SinglyLinkedList<>();

        for (int value = 100; value >= 10; value -= 10)
            singlyLinkedListOne.add(value);

        singlyLinkedListOne.printList();
        singlyLinkedListOne.batchRotateTwo(2, 4);
        System.out.println("batch rotate TWO --> ");
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

        // Second Problem Second Approach
        System.out.println("\n ========= Second Problem Second Approach ========= ");
        singlyLinkedListOne = new SinglyLinkedList<>();

        for (int value = 100; value >= 40; value -= 10)
            if (value != 50) singlyLinkedListOne.add(value);

        singlyLinkedListOne.printList();
        singlyLinkedListOne.batchRotateTwo(1, 3);
        System.out.println("batch rotate TWO --> ");
        singlyLinkedListOne.printList();
    }
}

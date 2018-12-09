package day9;

// The pointless Java LinkedList doesn't expose a way to save a position in the linked list, requiring the user to
// traverse the list every time you're adding add position N. Making adding O(n) i.s.o. the O(1) it is here.
final class LinkedMarbleCircle {
    private CircleNode currentMarble;

    LinkedMarbleCircle() {
        currentMarble = new CircleNode(0);
    }

    int addMarble(final int marble) {
        if (marble % 23 == 0) {
            moveCurrentBackward(7);
            final CircleNode removedNode = removeNode();
            return marble + removedNode.marble;
        }

        moveCurrentForward(2);
        insertNode(marble);
        return 0;
    }

    private CircleNode removeNode() {
        final CircleNode removedNode = currentMarble;
        removedNode.prev.next = removedNode.next;
        removedNode.next.prev = removedNode.prev;
        currentMarble = currentMarble.next;
        return removedNode;
    }

    private void insertNode(final int marble) {
        final CircleNode newNode = new CircleNode(marble, currentMarble.prev, currentMarble);
        newNode.prev.next = newNode;
        newNode.next.prev = newNode;
        currentMarble = newNode;
    }

    private void moveCurrentForward(final int offset) {
        for (int i = 0; i < offset; i++) {
            currentMarble = currentMarble.next;
        }
    }

    private void moveCurrentBackward(final int offset) {
        for (int i = 0; i < offset; i++) {
            currentMarble = currentMarble.prev;
        }
    }

    private static final class CircleNode {
        private final int marble;
        private CircleNode prev;
        private CircleNode next;

        private CircleNode(final int marble) {
            this.marble = marble;
            prev = this;
            next = this;
        }

        private CircleNode(final int marble, final CircleNode prev, final CircleNode next) {
            this.marble = marble;
            this.prev = prev;
            this.next = next;
        }
    }
}

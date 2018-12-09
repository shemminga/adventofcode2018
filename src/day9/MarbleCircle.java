package day9;

import java.util.ArrayList;
import java.util.List;

final class MarbleCircle {
    private final List<Integer> circle;
    private int currentMarbleIdx;

    MarbleCircle(final int maxMarble) {
        circle = new ArrayList<>(maxMarble + 1);
        circle.add(0);
        currentMarbleIdx = 0;
    }

    int addMarble(final int marble) {
        if (marble % 23 == 0) {
            moveCurrent(-7);
            final Integer removedMarble = circle.remove(currentMarbleIdx);
            return marble + removedMarble;
        }

        moveCurrent(2);
        circle.add(currentMarbleIdx, marble);
        return 0;
    }

    private void moveCurrent(final int offset) {
        currentMarbleIdx += offset;
        while (currentMarbleIdx < 0) {
            currentMarbleIdx = circle.size() + currentMarbleIdx;
        }
        while (currentMarbleIdx >= circle.size()) {
            currentMarbleIdx -= circle.size();
        }
    }
}

package day13;

import java.util.Map;

enum Turn {
    LEFT, STRAIGHT, RIGHT;

    private static final Map<Turn, Turn> ORDER = Map.of(LEFT, STRAIGHT, STRAIGHT, RIGHT, RIGHT, LEFT);

    public Turn getNext() {
        return ORDER.get(this);
    }
}

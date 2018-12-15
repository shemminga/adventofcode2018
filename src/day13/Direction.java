package day13;

import java.util.Map;

enum Direction {
    UP, LEFT, DOWN, RIGHT;

    private static final Map<Character, Direction> CHARACTER_DIRECTION_MAP = Map.of('^', UP, 'v', DOWN, '<', LEFT, '>', RIGHT);

    char getTrack() {
        switch (this) {
        case UP:
        case DOWN:
            return '|';
        case LEFT:
        case RIGHT:
            return '-';
        }
        throw new AssertionError();
    }

    Direction passCurve(final char curve) {
        if (curve == '\\') {
            switch (this) {
            case UP:
                return LEFT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return RIGHT;
            case LEFT:
                return UP;
            }
        }

        if (curve == '/') {
            switch (this) {
            case UP:
                return RIGHT;
            case RIGHT:
                return UP;
            case DOWN:
                return LEFT;
            case LEFT:
                return DOWN;
            }
        }

        throw new AssertionError();
    }

    Direction turnLeft() {
        if (this == RIGHT) {
            return UP;
        }
        return Direction.values()[this.ordinal() + 1];
    }

    Direction turnRight() {
        if (this == UP) {
            return RIGHT;
        }
        return Direction.values()[this.ordinal() - 1];
    }

    static Direction of(final char c) {
        return CHARACTER_DIRECTION_MAP.get(c);
    }
}

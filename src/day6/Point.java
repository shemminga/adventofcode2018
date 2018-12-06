package day6;

import java.util.Objects;

final class Point {
    private static char idCounter = 'A';

    private final char id;
    private final int x;
    private final int y;

    private Point(final int x, final int y) {
        id = idCounter++;
        if (idCounter == 'Z' + 1) {
            idCounter = 'A';
        }

        this.x = x;
        this.y = y;
    }

    char getId() {
        return id;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    static Point of(final String str) {
        final String[] parts = str.split(",");

        final int x = Integer.valueOf(parts[0].trim());
        final int y = Integer.valueOf(parts[1].trim());

        System.out.println(str + " -> " + x + " " + y);

        return new Point(x, y);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + id + ") " + x + ", " + y;
    }
}

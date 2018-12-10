package day10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class LightPoint {
    private static final Pattern PATTERN =
            Pattern.compile("^position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>$");

    private int x;
    private int y;
    private final int dx;
    private final int dy;

    static LightPoint of(final String str) {
        final Matcher matcher = PATTERN.matcher(str);
        matcher.find();

        final int x = Integer.valueOf(matcher.group(1));
        final int y = Integer.valueOf(matcher.group(2));
        final int dx = Integer.valueOf(matcher.group(3));
        final int dy = Integer.valueOf(matcher.group(4));

        return new LightPoint(x, y, dx, dy);
    }

    private LightPoint(final int x, final int y, final int dx, final int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    void stepForward() {
        x += dx;
        y += dy;
    }

    void stepBackward() {
        x -= dx;
        y -= dy;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}

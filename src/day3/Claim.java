package day3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Claim {
    private static final Pattern PATTERN = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");

    private final int id;
    private final int left;
    private final int top;
    private final int width;
    private final int height;

    private Claim(final int id, final int left, final int top, final int width, final int height) {
        this.id = id;
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    static Claim of(final CharSequence str) {

        final Matcher matcher = PATTERN.matcher(str);
        matcher.find();
        final int id = Integer.parseInt(matcher.group(1));
        final int left = Integer.parseInt(matcher.group(2));
        final int top = Integer.parseInt(matcher.group(3));
        final int width = Integer.parseInt(matcher.group(4));
        final int height = Integer.parseInt(matcher.group(5));

        return new Claim(id, left, top, width, height);
    }

    int getId() {
        return id;
    }

    int getLeft() {
        return left;
    }

    int getTop() {
        return top;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    int getRight() {
        return left + width;
    }

    int getBottom() {
        return top + height;
    }

    @Override
    public String toString() {
        return "#" + id + " @ " + left + ',' + top + ": " + width + 'x' + height;
    }
}

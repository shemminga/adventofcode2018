package day10;

import java.util.List;
import java.util.stream.Collectors;

final class SkyGrid {
    private final List<LightPoint> lightPoints;

    static SkyGrid of(final List<String> lines) {
        final List<LightPoint> lightPoints = lines.stream()
                .map(LightPoint::of)
                .collect(Collectors.toList());

        return new SkyGrid(lightPoints);
    }

    private SkyGrid(final List<LightPoint> lightPoints) {
        this.lightPoints = lightPoints;
    }

    void stepForward() {
        lightPoints.forEach(LightPoint::stepForward);
    }

    void stepBackward() {
        lightPoints.forEach(LightPoint::stepBackward);
    }

    long getSurface() {
        return (long) getWidth() * getHeight();
    }

    private int getWidth() {
        return getRight() - getLeft() + 1;
    }

    private int getHeight() {
        return getBottom() - getTop() + 1;
    }

    private int getBottom() {
        return lightPoints.stream()
                    .mapToInt(LightPoint::getY)
                    .max()
                    .orElseThrow(AssertionError::new);
    }

    private int getTop() {
        return lightPoints.stream()
                    .mapToInt(LightPoint::getY)
                    .min()
                    .orElseThrow(AssertionError::new);
    }

    private int getRight() {
        return lightPoints.stream()
                    .mapToInt(LightPoint::getX)
                    .max()
                    .orElseThrow(AssertionError::new);
    }

    private int getLeft() {
        return lightPoints.stream()
                    .mapToInt(LightPoint::getX)
                    .min()
                    .orElseThrow(AssertionError::new);
    }

    @Override
    public String toString() {
        final char[][] lines = new char[getHeight()][getWidth()];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length; j++) {
                lines[i][j] = ' ';
            }
        }

        final int left = getLeft();
        final int top = getTop();
        lightPoints.forEach(lp -> lines[lp.getY() - top][lp.getX() - left] = '#');

        final StringBuilder stringBuilder = new StringBuilder();
        for (final char[] line : lines) {
            stringBuilder.append(line);
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}

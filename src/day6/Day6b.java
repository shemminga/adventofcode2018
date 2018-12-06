package day6;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public final class Day6b {
    private static final int LIMIT = 10_000;

    private Day6b() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day6b.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final List<Point> points = lines.stream()
                .map(Point::of)
                .collect(Collectors.toList());

        final int width = points.stream()
                .mapToInt(Point::getX)
                .max()
                .orElseThrow(AssertionError::new) + 1;
        final int height = points.stream()
                .mapToInt(Point::getY)
                .max()
                .orElseThrow(AssertionError::new) + 1;

        int regionSize = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (totalDistance(i, j, points) < LIMIT) {
                    regionSize++;
                }
            }
        }

        System.out.println("Result: " + regionSize);
    }

    private static int totalDistance(final int i, final int j, final List<Point> points) {
        return points.stream()
                .mapToInt(point -> Math.abs(point.getX() - i) + Math.abs(point.getY() - j))
                .sum();
    }
}

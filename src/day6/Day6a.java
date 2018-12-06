package day6;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Day6a {
    private Day6a() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day6a.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final List<Point> points = lines.stream()
                .map(Point::of)
                .collect(Collectors.toList());

        final Grid grid = Grid.of(points);

        System.out.println(grid);

        grid.getAreas()
                .entrySet()
                .stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        final Map.Entry<Point, Grid.Area> largestArea = grid.getAreas()
                .entrySet()
                .stream()
                .filter(e -> !e.getValue()
                        .isInfinite())
                .max(Comparator.comparingInt(e -> e.getValue()
                        .getSize()))
                .orElseThrow(AssertionError::new);

        System.out.println("Result: " + largestArea.getKey() + " -> " + largestArea.getValue().getSize());
    }
}

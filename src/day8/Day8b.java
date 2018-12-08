package day8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

final class Day8b {
    private Day8b() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day8b.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final Deque<Integer> license = Arrays.stream(lines.get(0)
                .split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toCollection(ArrayDeque::new));

        final LicenseNode root = LicenseNode.of(license);

        System.out.println("Result: " + root.checksumB());
    }
}

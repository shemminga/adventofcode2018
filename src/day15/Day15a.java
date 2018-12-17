package day15;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

final class Day15a {
    private Day15a() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day15a.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));
        final char[][] grid = lines.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        new BattleSimOfTheRepublic(grid).combat();
    }
}

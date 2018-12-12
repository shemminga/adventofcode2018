package day12;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

final class Day12a {
    private Day12a() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day12a.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final ConwaysGameOfPlants plants = ConwaysGameOfPlants.of(lines);
        System.out.println(plants);

        for (int i = 0; i < 20; i++) {
            plants.nextGeneration();
            System.out.println(plants);
        }
    }
}

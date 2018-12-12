package day12;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

final class Day12b {
    private Day12b() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day12b.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final ConwaysGameOfPlants plants = ConwaysGameOfPlants.of(lines);
        System.out.println(plants);

        String prevState = "";
        String state = plants.getState();
        while (!prevState.equals(state)) {
            plants.nextGeneration();
            System.out.println(plants);

            prevState = state;
            state = plants.getState();
        }

        for (int i = 0; i < 5; i++) {
            plants.nextGeneration();
            System.out.println(plants);
        }
    }
}

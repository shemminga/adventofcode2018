package day10;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

final class Day10ab {
    private Day10ab() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day10ab.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final SkyGrid skyGrid = SkyGrid.of(lines);

        long lastSurface = Long.MAX_VALUE;
        long second = 0;
        while (skyGrid.getSurface() < lastSurface) {
            lastSurface = skyGrid.getSurface();
            skyGrid.stepForward();
            second++;
        }

        skyGrid.stepBackward();
        second--;
        System.out.println(skyGrid);
        System.out.println("At second " + second);
    }
}

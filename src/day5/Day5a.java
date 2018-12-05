package day5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class Day5a {
    private Day5a() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day5a.class.getResource("input.txt");

        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final String result = Reactor.reactFull(lines.get(0));

        System.out.println("Result: " + result.length() + ' ' + result);
    }

}

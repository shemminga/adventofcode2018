package day5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Day5b {
    private Day5b() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day5b.class.getResource("input.txt");

        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final Map<Character, String> polymersPerElement = getAllCharacters(lines.get(0)).stream()
                .collect(Collectors.toMap(c -> c, c -> Reactor.reactFull(removeAllChars(lines.get(0), c))));

        final Map.Entry<Character, String> shortestPolymer = polymersPerElement.entrySet()
                .stream()
                .min(Comparator.comparingInt(e -> e.getValue()
                        .length()))
                .orElseThrow(AssertionError::new);

        System.out.println("Remove " + shortestPolymer.getKey() + " to get length " + shortestPolymer.getValue()
                .length() + ' ' + shortestPolymer.getValue());
    }

    private static List<Character> getAllCharacters(final String str) {
        return str.chars()
                .mapToObj(c -> Character.toUpperCase((char) c))
                .sorted()
                .distinct()
                .collect(Collectors.toList());
    }

    private static String removeAllChars(final String str, final char c) {
        final String upperC = String.valueOf(Character.toUpperCase(c));
        final String lowerC = String.valueOf(Character.toLowerCase(c));

        return str.replace(upperC, "").replace(lowerC, "");
    }
}

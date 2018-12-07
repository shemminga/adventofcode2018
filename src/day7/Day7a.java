package day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class Day7a {
    private Day7a() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day7a.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final Map<Character, List<Character>> toFromMap = ToFromMappingReader.read(lines);
        printSolution(toFromMap);
    }

    private static void printSolution(final Map<Character, List<Character>> toFromMap) {
        System.out.print("Result: ");
        while (!toFromMap.isEmpty()) {
            printAndRemoveNextStep(toFromMap);
        }
        System.out.println();
    }

    private static void printAndRemoveNextStep(final Map<Character, List<Character>> toFromMap) {
        final Character nextStep = toFromMap.entrySet()
                .stream()
                .filter(e -> e.getValue()
                        .isEmpty())
                .map(Entry::getKey)
                .sorted()
                .findFirst()
                .orElseThrow(AssertionError::new);

        System.out.print(nextStep);

        toFromMap.remove(nextStep);
        toFromMap.values()
                .forEach(list -> list.remove(nextStep));
    }
}

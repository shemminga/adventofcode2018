package day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

final class Day7b {
    private static final Pattern PATTERN =
            Pattern.compile("^Step (.) must be finished before step (.) can begin\\.$");
    private static final int MAX_WORKERS = 5;
    private static final int EXTRA_DELAY = 60;

    private Day7b() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day7b.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final Map<Character, List<Character>> toFromMap = ToFromMappingReader.read(lines);

        final int result = calculateSolution(toFromMap);
        System.out.println("Result: " + result);
    }

    private static int calculateSolution(final Map<Character, List<Character>> toFromMap) {
        final Map<Character, Integer> itemDoneMap = new HashMap<>();
        int timer = 0;

        while (!toFromMap.isEmpty()) {
            printStatus("Until ", timer, itemDoneMap, toFromMap);

            clearDoneActions(timer, itemDoneMap, toFromMap);
            assignIdleWorkers(timer, itemDoneMap, toFromMap);
            printStatus("  `-> ", timer, itemDoneMap, toFromMap);
            timer = delayUntilNextAction(timer, itemDoneMap);
        }

        return timer;
    }

    private static void printStatus(final String prefix, final int timer, final Map<Character, Integer> itemDoneMap,
            final Map<Character, List<Character>> toFromMap) {
        final String activeWorkers = itemDoneMap.keySet()
                .stream()
                .map(String::valueOf)
                .sorted()
                .collect(Collectors.joining());
        final String nodesToGo = toFromMap.keySet()
                .stream()
                .map(String::valueOf)
                .sorted()
                .collect(Collectors.joining());
        System.out.printf("%s time: %d -> Active workers: %d [%s]  Nodes to go: %d [%s]",
                prefix, timer, itemDoneMap.size(), activeWorkers, toFromMap.size(), nodesToGo);
        System.out.println();
    }

    private static void clearDoneActions(final int timer, final Map<Character, Integer> itemDoneMap,
            final Map<Character, List<Character>> toFromMap) {
        final List<Character> doneItems = itemDoneMap.entrySet()
                .stream()
                .filter(e -> e.getValue() <= timer)
                .map(Entry::getKey)
                .collect(Collectors.toList());

        doneItems.forEach(item -> {
            itemDoneMap.remove(item);
            toFromMap.remove(item);
            toFromMap.values()
                    .forEach(list -> list.remove(item));
        });
    }

    private static void assignIdleWorkers(final int timer, final Map<Character, Integer> itemDoneMap,
            final Map<Character, List<Character>> toFromMap) {
        if (itemDoneMap.size() >= MAX_WORKERS) {
            return;
        }

        final List<Character> todo = availableWorkItems(itemDoneMap, toFromMap, MAX_WORKERS - itemDoneMap.size());
        todo.forEach(item -> itemDoneMap.put(item, timer + EXTRA_DELAY + item - 'A' + 1));
    }

    private static List<Character> availableWorkItems(final Map<Character, Integer> itemDoneMap,
            final Map<Character, List<Character>> toFromMap, final int maxItems) {
        return toFromMap.entrySet()
                .stream()
                .filter(e -> e.getValue()
                        .isEmpty())
                .map(Entry::getKey)
                .filter(item -> !itemDoneMap.containsKey(item))
                .sorted()
                .limit(maxItems)
                .collect(Collectors.toList());
    }

    private static int delayUntilNextAction(final int timer, final Map<Character, Integer> itemDoneMap) {
        return itemDoneMap.values()
                .stream()
                .min(Comparator.naturalOrder())
                .orElse(timer);
    }
}

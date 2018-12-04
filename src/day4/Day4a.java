package day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Day4a {
    private Day4a() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day4a.class.getResource("input.txt");

        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));
        lines.sort(Comparator.naturalOrder());

        final List<Shift> shifts = ShiftFactory.toShifts(lines);

        final Map<Integer, List<Shift>> shiftsPerGuard = shifts.stream()
                .collect(Collectors.groupingBy(Shift::getId));

        final Integer sleepyHeadGuard = getSleepyHeadGuard(shiftsPerGuard);
        final int popularSleepMinute = getPopularSleepMinute(shiftsPerGuard.get(sleepyHeadGuard));

        System.out.println(sleepyHeadGuard * popularSleepMinute);
    }

    private static int getPopularSleepMinute(final List<Shift> shifts) {
        final Map<Integer, Integer> sleepPerMinute = IntStream.range(0, 60)
                .boxed()
                .collect(Collectors.toMap(x -> x, minute -> shifts.stream()
                        .mapToInt(shift -> shift.isAsleepAt(minute) ? 1 : 0)
                        .sum()));

        final Map.Entry<Integer, Integer> mostPopularSleepMinute =
                Collections.max(sleepPerMinute.entrySet(), Comparator.comparingInt(Map.Entry::getValue));

        System.out.format("Most popular sleep minute %d (%d times)", mostPopularSleepMinute.getKey(),
                mostPopularSleepMinute.getValue());
        System.out.println();

        return mostPopularSleepMinute.getKey();
    }

    private static Integer getSleepyHeadGuard(final Map<Integer, List<Shift>> shiftsPerGuard) {
        final Map<Integer, Integer> timeAsleepPerGuard = shiftsPerGuard.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()
                        .stream()
                        .mapToInt(Shift::getTimeAsleep)
                        .sum()));

        final Map.Entry<Integer, Integer> sleepyHead =
                Collections.max(timeAsleepPerGuard.entrySet(), Comparator.comparingInt(Map.Entry::getValue));

        System.out.format("Sleepy head guard #%d (asleep for %d minutes)", sleepyHead.getKey(), sleepyHead.getValue());
        System.out.println();

        return sleepyHead.getKey();
    }
}

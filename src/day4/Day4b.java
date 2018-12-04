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

public final class Day4b {
    private Day4b() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day4b.class.getResource("input.txt");

        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));
        lines.sort(Comparator.naturalOrder());

        final List<Shift> shifts = ShiftFactory.toShifts(lines);

        final Map<Integer, List<Shift>> shiftsPerGuard = shifts.stream()
                .collect(Collectors.groupingBy(Shift::getId));

        final GuardMinuteFrequency max = shiftsPerGuard.entrySet()
                .stream()
                .map(entry -> getGuardMinuteFrequency(entry.getKey(), entry.getValue()))
                .max(Comparator.comparingInt(GuardMinuteFrequency::getFrequency))
                .orElseThrow(AssertionError::new);

        System.out.format("Guard #%d is most often asleep at minute %d (%d times)", max.guardId, max.minute, max.frequency);
        System.out.println();
        System.out.println(max.guardId * max.minute);
    }

    private static GuardMinuteFrequency getGuardMinuteFrequency(final int guardId, final List<Shift> shifts) {
        final Map<Integer, Integer> sleepPerMinute = IntStream.range(0, 60)
                .boxed()
                .collect(Collectors.toMap(x -> x, minute -> shifts.stream()
                        .mapToInt(shift -> shift.isAsleepAt(minute) ? 1 : 0)
                        .sum()));

        final Map.Entry<Integer, Integer> mostPopularSleepMinute =
                Collections.max(sleepPerMinute.entrySet(), Comparator.comparingInt(Map.Entry::getValue));

        return new GuardMinuteFrequency(guardId, mostPopularSleepMinute.getKey(), mostPopularSleepMinute.getValue());
    }

    private static final class GuardMinuteFrequency {
        private final int guardId;
        private final int minute;
        private final int frequency;

        private GuardMinuteFrequency(final int guardId, final int minute, final int frequency) {
            this.guardId = guardId;
            this.minute = minute;
            this.frequency = frequency;
        }

        public int getFrequency() {
            return frequency;
        }
    }
}

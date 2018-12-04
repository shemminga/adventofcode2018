package day4;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class Shift {
    private final int id;
    private final int month;
    private final int day;

    private final boolean[] asleep = new boolean[60];

    Shift(final int id, final int month, final int day) {
        this.id = id;
        this.month = month;
        this.day = day;
    }

    void asleepFrom(final int minute) {
        for (int i = minute; i < asleep.length; i++) {
            asleep[i] = true;
        }
    }

    void awakeFrom(final int minute) {
        for (int i = minute; i < asleep.length; i++) {
            asleep[i] = false;
        }
    }

    public int getId() {
        return id;
    }

    int getMonth() {
        return month;
    }

    int getDay() {
        return day;
    }

    int getTimeAsleep() {
        return IntStream.range(0, asleep.length)
                .map(i -> asleep[i] ? 1 : 0)
                .sum();
    }

    boolean isAsleepAt(final int minute) {
        return asleep[minute];
    }

    @Override
    public String toString() {
        final String schedule = IntStream.range(0, asleep.length)
                .mapToObj(i -> asleep[i] ? "#" : ".")
                .collect(Collectors.joining());

        return String.format("%02d-%02d #%5d: %s", month, day, id, schedule);
    }
}

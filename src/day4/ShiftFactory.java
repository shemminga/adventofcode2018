package day4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ShiftFactory {
    private static final Pattern SHIFT_START =
            Pattern.compile("^\\[1518-(\\d\\d)-(\\d\\d) (\\d\\d):\\d\\d] Guard #(\\d+) begins shift$");
    private static final Pattern FALLS_ASLEEP =
            Pattern.compile("^\\[1518-(\\d\\d)-(\\d\\d) \\d\\d:(\\d\\d)] falls asleep$");
    private static final Pattern WAKES_UP = Pattern.compile("^\\[1518-(\\d\\d)-(\\d\\d) \\d\\d:(\\d\\d)] wakes up$");

    static List<Shift> toShifts(final List<String> lines) {
        final List<Shift> shifts = new ArrayList<>();
        Shift current = null;

        for (final String line : lines) {
            final Matcher shiftStartMatcher = SHIFT_START.matcher(line);
            if (shiftStartMatcher.matches()) {
                final int month = Integer.parseInt(shiftStartMatcher.group(1));
                final int day = Integer.parseInt(shiftStartMatcher.group(2));
                final int hour = Integer.parseInt(shiftStartMatcher.group(3));
                final int id = Integer.parseInt(shiftStartMatcher.group(4));
                current = newShift(id, month, day, hour);
                shifts.add(current);
                continue;
            }

            Objects.requireNonNull(current);

            final Matcher fallsAsleepMatcher = FALLS_ASLEEP.matcher(line);
            if (fallsAsleepMatcher.matches()) {
                final int month = Integer.parseInt(fallsAsleepMatcher.group(1));
                final int day = Integer.parseInt(fallsAsleepMatcher.group(2));
                final int minute = Integer.parseInt(fallsAsleepMatcher.group(3));

                if (current.getMonth() != month || current.getDay() != day) {
                    throw new RuntimeException("Line has a date issue: " + line);
                }

                current.asleepFrom(minute);
                continue;
            }

            final Matcher wakesUpMatcher = WAKES_UP.matcher(line);
            if (wakesUpMatcher.matches()) {
                final int month = Integer.parseInt(wakesUpMatcher.group(1));
                final int day = Integer.parseInt(wakesUpMatcher.group(2));
                final int minute = Integer.parseInt(wakesUpMatcher.group(3));

                if (current.getMonth() != month || current.getDay() != day) {
                    throw new RuntimeException("Line has a date issue: " + line);
                }

                current.awakeFrom(minute);
            }
        }

        return shifts;
    }

    private static Shift newShift(final int id, int month, int day, final int hour) {
        if (hour == 23) {
            final LocalDate date = LocalDate.of(1518, month, day)
                    .plusDays(1);

            month = date.getMonthValue();
            day = date.getDayOfMonth();
        }

        return new Shift(id, month, day);
    }
}

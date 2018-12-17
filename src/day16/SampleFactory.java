package day16;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

final class SampleFactory {
    private SampleFactory() {
    }

    static List<Sample> toSamples(final Deque<String> lines) {
        final List<Sample> samples = new ArrayList<>();
        while (!lines.isEmpty() && !lines.peek()
                .isEmpty()) {
            final String beforeLine = lines.poll();
            final String instructionLine = lines.poll();
            final String afterLine = lines.poll();

            samples.add(Sample.of(beforeLine, instructionLine, afterLine));

            if (!lines.isEmpty()) {
                lines.removeFirst();
            }
        }
        return samples;
    }
}

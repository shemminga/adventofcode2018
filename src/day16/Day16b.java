package day16;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

final class Day16b {
    private Day16b() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day16b.class.getResource("input.txt");
        final List<String> linesList = Files.readAllLines(Paths.get(input.toURI()));
        final Deque<String> lines = new ArrayDeque<>(linesList);

        final List<Sample> samples = SampleFactory.toSamples(lines);
        final Opcode[] opcodeTable = new OpcodeTranslator(samples).translate();

        final int[] regs = Emulator.emulate(lines, opcodeTable);
        System.out.println(Arrays.toString(regs));
    }
}

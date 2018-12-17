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
import java.util.Map;
import java.util.stream.Collectors;

final class Day16a {
    private Day16a() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day16a.class.getResource("input.txt");
        final List<String> linesList = Files.readAllLines(Paths.get(input.toURI()));
        final Deque<String> lines = new ArrayDeque<>(linesList);

        final List<Sample> samples = SampleFactory.toSamples(lines);

        final Map<Sample, List<Opcode>> sampleOpcodesMap = samples.stream()
                .collect(Collectors.toMap(s -> s, s -> Opcode.stream()
                        .filter(opcode -> Arrays.equals(s.getRegsAfter(),
                                opcode.applyToCopy(s.getA(), s.getB(), s.getC(), s.getRegsBefore())))
                        .collect(Collectors.toList())));

        final Map<Sample, List<Opcode>> filteredMap = sampleOpcodesMap.entrySet()
                .stream()
                .filter(e -> e.getValue().size() >= 3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(filteredMap);
        System.out.println();
        System.out.println("Result: " + filteredMap.size());
    }

}

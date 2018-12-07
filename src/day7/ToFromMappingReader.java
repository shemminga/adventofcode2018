package day7;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class ToFromMappingReader {
    private static final Pattern PATTERN = Pattern.compile("^Step (.) must be finished before step (.) can begin\\.$");

    private ToFromMappingReader() {
    }

    static Map<Character, List<Character>> read(final List<String> lines) {
        final Map<Character, List<Character>> toFromMap = new HashMap<>();
        lines.stream()
                .map(ToFromMappingReader::extractToFrom)
                .forEach(e -> {
                    toFromMap.computeIfAbsent(e.getKey(), ArrayList::new)
                            .add(e.getValue());
                    toFromMap.computeIfAbsent(e.getValue(), ArrayList::new);
                });
        return toFromMap;
    }

    private static Entry<Character, Character> extractToFrom(final String str) {
        final Matcher matcher = PATTERN.matcher(str);
        matcher.find();
        final Character from = matcher.group(1)
                .charAt(0);
        final Character to = matcher.group(2)
                .charAt(0);

        return new SimpleEntry<>(to, from);
    }
}

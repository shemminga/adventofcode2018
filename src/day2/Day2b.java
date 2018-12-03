package day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public final class Day2b {
    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day2b.class.getResource("input.txt");

        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        lines.forEach(str -> findSisterAndPrint(str, lines));
    }

    private static void findSisterAndPrint(final String str, final List<String> lines) {
        final List<String> oneOffs = lines.stream().filter(str2 -> isOneDiff(str, str2)).collect(Collectors.toList());

        if (!oneOffs.isEmpty()) {
            System.out.println(str + " -> " + oneOffs);
        }
    }

    private static boolean isOneDiff(final String str1, final String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        final char[] chars1 = str1.toCharArray();
        final char[] chars2 = str2.toCharArray();

        int diffCnt = 0;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                diffCnt++;
            }
        }

        return diffCnt == 1;
    }
}

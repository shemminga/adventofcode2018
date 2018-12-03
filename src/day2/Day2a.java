package day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day2a {
    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day2a.class.getResource("input.txt");

        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final long exact2 = lines.stream().filter(str -> hasExactlyXOfAnyLetter(2, str)).count();
        final long exact3 = lines.stream().filter(str -> hasExactlyXOfAnyLetter(3, str)).count();

        System.out.println(exact2 + " " + exact3 + ' ' + exact2 * exact3);
    }

    private static boolean hasExactlyXOfAnyLetter(final int x, final String str) {
        final char[] chars = str.toCharArray();

        for (final char char1 : chars) {
            int sameCnt = 0;
            for (final char char2 : chars) {
                if (char1 == char2) {
                    sameCnt++;
                }
            }

            if (sameCnt == x) {
                return true;
            }
        }
        return false;
    }
}

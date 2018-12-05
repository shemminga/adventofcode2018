package day5;

import java.util.Arrays;

final class Reactor {
    private Reactor() {
    }

    static String reactFull(final String str) {
        char[] chars1 = str.toCharArray();
        char[] chars2 = new char[chars1.length];

        while (!Arrays.equals(chars1, chars2)) {
            final String s = new String(chars1);
            System.out.println("Iteration: " + s);

            chars1 = s.trim()
                    .toCharArray(); // Shrink array
            chars2 = new char[chars1.length];

            reactOnce(chars1, chars2);

            final char[] charsTmp = chars1;
            chars1 = chars2;
            chars2 = charsTmp;
        }

        return new String(chars1).trim();
    }

    private static void reactOnce(final char[] chars1, final char[] chars2) {
        int idx1 = 0;
        int idx2 = 0;

        while (idx1 < chars1.length - 1) {
            if (areComplements(chars1[idx1], chars1[idx1 + 1])) {
                idx1 += 2;
                continue;
            }

            chars2[idx2] = chars1[idx1];
            idx1++;
            idx2++;
        }

        if (idx1 == chars1.length - 1) {
            chars2[idx2] = chars1[idx1];
        }
    }

    private static boolean areComplements(final char c1, final char c2) {
        return c1 != c2 && Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }
}

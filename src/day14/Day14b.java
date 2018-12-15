package day14;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class Day14b {
    private static final String INPUT = "793061";

    private final List<Integer> input;
    private final List<Integer> scores = new ArrayList<>();
    private int elf1Pos = 1;
    private int elf2Pos = 2;

    private Day14b() {
        scores.add(Integer.MIN_VALUE); // Bogus
        scores.add(3);
        scores.add(7);

        input = INPUT.chars()
                .map(c -> c - '0')
                .boxed()
                .collect(Collectors.toList());
    }

    private void solve() {
        printBoard();

        while (!isSolved()) {
            cookOneRound();
//            printBoard();
        }

        final String board = scores.stream()
                .skip(1)
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("Result: " + board.indexOf(INPUT));
    }

    private boolean isSolved() {
        if (scores.size() < INPUT.length()) {
            return false;
        }

        if (scores.subList(scores.size() - INPUT.length(), scores.size())
                .equals(input)) {
            return true;
        }

        if (scores.size() - 1 >= INPUT.length()) {
            return scores.subList(scores.size() - INPUT.length() - 1, scores.size() - 1)
                    .equals(input);
        }

        return false;
    }

    private void cookOneRound() {
        final int newScore = scores.get(elf1Pos) + scores.get(elf2Pos);

        if (newScore >= 10) {
            scores.add(1);
            scores.add(newScore - 10);
        } else {
            scores.add(newScore);
        }

        elf1Pos = newPos(elf1Pos);
        elf2Pos = newPos(elf2Pos);
    }

    private int newPos(final int oldPos) {
        int newPos = oldPos + 1 + scores.get(oldPos);

        while (newPos >= scores.size()) {
            newPos -= scores.size() - 1;
        }

        return newPos;
    }

    private void printBoard() {
        for (int i = 1; i < scores.size(); i++) {
            if (elf1Pos == i) {
                System.out.print("(");
            } else if (elf2Pos == i) {
                System.out.print("[");
            } else {
                System.out.print(" ");
            }

            System.out.print(scores.get(i));

            if (elf1Pos == i) {
                System.out.print(")");
            } else if (elf2Pos == i) {
                System.out.print("]");
            } else {
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    public static void main(final String... args) {
        new Day14b().solve();
    }
}

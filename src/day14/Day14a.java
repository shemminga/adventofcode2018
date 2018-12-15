package day14;

final class Day14a {
    private static final int INPUT = 793061;

    private final int[] scores = new int[INPUT + 10 + 2];
    private int elf1Pos = 1;
    private int elf2Pos = 2;
    private int lastPos = 2;

    private Day14a() {
        scores[1] = 3;
        scores[2] = 7;
    }

    private void solve() {
        printBoard();

        while (lastPos < INPUT + 10) {
            cookOneRound();
//            printBoard();
        }

        printSolution();
    }

    private void cookOneRound() {
        final int newScore = scores[elf1Pos] + scores[elf2Pos];

        if (newScore >= 10) {
            lastPos++;
            scores[lastPos] = 1;
            lastPos++;
            scores[lastPos] = newScore - 10;
        } else {
            lastPos++;
            scores[lastPos] = newScore;
        }

        elf1Pos = newPos(elf1Pos);
        elf2Pos = newPos(elf2Pos);
    }

    private int newPos(final int oldPos) {
        int newPos = oldPos + 1 + scores[oldPos];

        while (newPos > lastPos) {
            newPos -= lastPos;
        }

        return newPos;
    }

    private void printBoard() {
        for (int i = 1; i <= lastPos; i++) {
            if (elf1Pos == i) {
                System.out.print("(");
            } else if (elf2Pos == i) {
                System.out.print("[");
            } else {
                System.out.print(" ");
            }

            System.out.print(scores[i]);

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

    private void printSolution() {
        System.out.print("Result: ");

        for (int i = INPUT + 1; i <= INPUT + 10; i++) {
            System.out.print(scores[i]);
        }

        System.out.println();
    }

    public static void main(final String... args) {
        new Day14a().solve();
    }
}

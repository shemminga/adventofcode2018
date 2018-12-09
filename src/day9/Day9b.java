package day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

final class Day9b {
    private static final int PLAYERS = 404;
    private static final int LAST_MARBLE = 7_185_200;

    private Day9b() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final LinkedMarbleCircle circle = new LinkedMarbleCircle();
        final long[] scores = new long[PLAYERS + 1];

        int currentPlayer = 1;
        for (int marble = 1; marble <= LAST_MARBLE; marble++) {
            final int moveScore = circle.addMarble(marble);

            scores[currentPlayer] += moveScore;

            currentPlayer++;
            if (currentPlayer > PLAYERS) {
                currentPlayer -= PLAYERS;
            }
        }

        for (int i = 1; i <= PLAYERS; i++) {
            System.out.println("Player #" + i + ": " + scores[i]);
        }

        final long highScore = Arrays.stream(scores)
                .max()
                .orElseThrow(AssertionError::new);
        System.out.println("High score: " + highScore);
    }
}

package day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

final class Day9a {
    private static final int PLAYERS = 404;
    private static final int LAST_MARBLE = 71852;

    private Day9a() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final MarbleCircle circle = new MarbleCircle(LAST_MARBLE);
        final int[] scores = new int[PLAYERS + 1];

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

        final int highScore = Arrays.stream(scores)
                .max()
                .orElseThrow(AssertionError::new);
        System.out.println("High score: " + highScore);
    }
}

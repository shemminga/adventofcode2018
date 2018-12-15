package day13;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

final class Day13a {
    private Day13a() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day13a.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final TrackLayoutA tracks = TrackLayoutA.of(lines);

        printCarts(tracks);
        while (tracks.getCollidedCarts().isEmpty()) {
            tracks.doTick();
            printCarts(tracks);
        }

        final Cart firstCollider = tracks.getCollidedCarts()
                .get(0);

        System.out.println("Result: " + firstCollider.getX() + ',' + firstCollider.getY());
    }

    private static void printCarts(final TrackLayoutA tracks) {
        tracks.getCarts()
                .forEach(cart -> {
                    System.out.println(tracks.getGrid()[cart.getY()][cart.getX()] + " " + cart);
                });
        System.out.println();
    }
}

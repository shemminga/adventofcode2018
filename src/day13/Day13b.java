package day13;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

final class Day13b {
    private Day13b() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day13b.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));

        final TrackLayoutB tracks = TrackLayoutB.of(lines);

        printCarts(tracks);
        while (tracks.getCarts().size() > 1) {
            tracks.doTick();
            printCarts(tracks);
        }

        final Cart lastCart = tracks.getCarts()
                .get(0);
        System.out.println("Result: " + lastCart.getX() + ',' + lastCart.getY());
    }

    private static void printCarts(final TrackLayoutB tracks) {
        tracks.getCarts()
                .forEach(cart -> {
                    System.out.println(tracks.getGrid()[cart.getY()][cart.getX()] + " " + cart);
                });
        System.out.println();
    }
}

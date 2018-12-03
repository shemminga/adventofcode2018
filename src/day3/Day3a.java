package day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public final class Day3a {
    private Day3a() {
        throw new AssertionError();
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day3a.class.getResource("input.txt");

        final List<Claim> claims = Files.readAllLines(Paths.get(input.toURI()))
                .stream()
                .map(Claim::of)
                .collect(Collectors.toList());

        final Fabric fabric = Fabric.of(claims);

        fabric.print(456, 139, 473, 158);
        fabric.print(623, 435, 637, 461);
        fabric.print(622, 443, 652, 458);
        System.out.println("Overlap: " + fabric.getOverlap());
    }
}

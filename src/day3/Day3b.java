package day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public final class Day3b {
    private Day3b() {
        throw new AssertionError();
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day3b.class.getResource("input.txt");

        final List<Claim> claims = Files.readAllLines(Paths.get(input.toURI()))
                .stream()
                .map(Claim::of)
                .collect(Collectors.toList());

        final List<Claim> noOverlaps = claims.stream()
                .filter(claim -> !hasOverlap(claim, claims))
                .collect(Collectors.toList());

        noOverlaps.forEach(System.out::println);
    }

    private static boolean hasOverlap(final Claim claim, final List<Claim> claims) {
        return claims.stream()
                .filter(claim2 -> !claim.equals(claim2))
                .anyMatch(claim2 -> hasOverlap(claim, claim2) || hasOverlap(claim2, claim));
    }

    private static boolean hasOverlap(final Claim claim1, final Claim claim2) {
        return pointInClaim(claim1.getLeft(), claim1.getTop(), claim2) ||
                pointInClaim(claim1.getLeft(), claim1.getBottom() - 1, claim2) ||
                pointInClaim(claim1.getRight() - 1, claim1.getTop(), claim2) ||
                pointInClaim(claim1.getRight() - 1, claim1.getBottom() - 1, claim2);
    }

    private static boolean pointInClaim(final int x, final int y, final Claim claim) {
        return pointInBbox(x, y, claim.getLeft(), claim.getTop(), claim.getRight(), claim.getBottom());
    }

    private static boolean pointInBbox(final int x, final int y, final int left, final int top, final int right,
            final int bottom) {
        return (x >= left) && (x < right) && (y >= top) && (y < bottom);
    }
}

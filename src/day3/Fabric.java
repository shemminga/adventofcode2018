package day3;

import java.util.List;

final class Fabric {
    private final int[][] grid;

    private Fabric(final int maxRight, final int maxBottom) {
        grid = new int[maxRight][maxBottom];
    }

    static Fabric of(final List<Claim> claims) {
        final int maxRight = claims.stream()
                .mapToInt(Claim::getRight)
                .max()
                .orElseThrow(AssertionError::new);
        final int maxBottom = claims.stream()
                .mapToInt(Claim::getRight)
                .max()
                .orElseThrow(AssertionError::new);

        final Fabric fabric = new Fabric(maxRight, maxBottom);
        fabric.register(claims);
        return fabric;
    }

    private void register(final List<Claim> claims) {
        claims.forEach(claim -> {
            for (int i = claim.getLeft(); i < claim.getRight(); i++) {
                for (int j = claim.getTop(); j < claim.getBottom(); j++) {
                    grid[i][j]++;
                }
            }
        });
    }

    int getOverlap() {
        int overlap = 0;
        for (final int[] sqinches : grid) {
            for (final int sqinch : sqinches) {
                if (sqinch > 1) {
                    overlap++;
                }
            }
        }
        return overlap;
    }

    void print() {
        print(0, 0, grid.length, grid[0].length);
    }

    void print(final int left, final int top, final int right, final int bottom) {
        System.out.println(left + ".." + right + ", " + top + ".." + bottom);
        System.out.println();

        for (int i = left; i < right; i++) {
            for (int j = top; j < bottom; j++) {
                if (grid[i][j] == 0) {
                    System.out.print(" .");
                } else {
                    System.out.format("%2d", grid[i][j]);
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println(left + ".." + right + ", " + top + ".." + bottom);
    }
}

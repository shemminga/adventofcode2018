package day15;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

final class GridPrinter {
    private GridPrinter() {
    }

    static void printGrid(final int round, final char[][] grid, final List<Soldier> soldiers) {
        System.out.println("Round " + round + ':');
        final char[][] copy = copyGrid(grid);
        printCopy(soldiers, copy);
    }

    static char[][] copyGrid(final char[][] grid) {
        final char[][] copy = new char[grid.length][grid[0].length];

        for (int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, copy[y], 0, grid[y].length);
        }
        return copy;
    }

    private static void printCopy(final List<Soldier> soldiers, final char[][] copy) {
        soldiers.forEach(s -> copy[s.getY()][s.getX()] = s.getType().name().charAt(0));

        for (int y = 0; y < copy.length; y++) {
            System.out.print(copy[y]);
            System.out.print("   ");

            final int y1 = y; // Make effectively final for lambda.
            final String soldiersHp = soldiers.stream()
                    .filter(s -> s.getY() == y1)
                    .sorted(Comparator.comparingInt(Soldier::getX))
                    .map((Soldier s) -> s.getType().name().charAt(0) + "(" + s.getHitpoints() + ')')
                    .collect(Collectors.joining(", "));

            System.out.println(soldiersHp);
        }

        System.out.println();
    }
}

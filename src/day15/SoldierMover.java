package day15;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

final class SoldierMover {
    private SoldierMover() {
    }

    static void move(final Soldier soldier, final char[][] grid, final List<Soldier> soldiers) {
        final Soldier[][] soldierGrid = new Soldier[grid.length][grid[0].length];
        soldiers.stream()
                .filter(Soldier::isAlive)
                .forEach(s -> soldierGrid[s.getY()][s.getX()] = s);

        final int[][] distanceGrid = createDistanceGrid(soldier.getCoords(), grid, soldierGrid);

        final Optional<Coords> optDestination = soldiers.stream()
                .filter(Soldier::isAlive)
                .filter(s -> !soldier.equals(s))
                .filter(s -> s.getType() != soldier.getType())
                .flatMap(Soldier::adjacents)
                .filter(c -> distanceGrid[c.getY()][c.getX()] < Integer.MAX_VALUE)
                .min(Comparator.comparingInt((Coords c) -> distanceGrid[c.getY()][c.getX()])
                        .thenComparingInt(Coords::getY)
                        .thenComparingInt(Coords::getX));

        if (!optDestination.isPresent()) {
            // No destination reachable
            return;
        }

        final Coords destination = optDestination.get();

        if (destination.getY() == soldier.getY() && destination.getX() == soldier.getX()) {
            // Already there
            return;
        }

        final int[][] reverseDistanceGrid = createDistanceGrid(destination, grid, soldierGrid);

        final Optional<Coords> optMove = soldier.adjacents()
                .filter(c -> reverseDistanceGrid[c.getY()][c.getX()] < Integer.MAX_VALUE)
                .min(Comparator.comparingInt((Coords c) -> reverseDistanceGrid[c.getY()][c.getX()])
                        .thenComparingInt(Coords::getY)
                        .thenComparingInt(Coords::getX));

        optMove.ifPresent(c -> {
            soldier.setY(c.getY());
            soldier.setX(c.getX());
        });
    }

    private static int[][] createDistanceGrid(final Coords origin, final char[][] grid, final Soldier[][] soldierGrid) {
        final int[][] distances = new int[grid.length][grid[0].length];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                distances[y][x] = Integer.MAX_VALUE;
            }
        }

        final PriorityQueue<Coords> queue = new PriorityQueue<>(Comparator.comparingInt(c -> distances[c.getY()][c.getX()]));
        distances[origin.getY()][origin.getX()] = 0;
        queue.add(new Coords(origin.getY(), origin.getX()));

        while (!queue.isEmpty()) {
            final Coords coords = queue.poll();

            setDistance(coords.getY() - 1, coords.getX(), distances[coords.getY()][coords.getX()], distances, grid, soldierGrid, queue);
            setDistance(coords.getY() + 1, coords.getX(), distances[coords.getY()][coords.getX()], distances, grid, soldierGrid, queue);
            setDistance(coords.getY(), coords.getX() - 1, distances[coords.getY()][coords.getX()], distances, grid, soldierGrid, queue);
            setDistance(coords.getY(), coords.getX() + 1, distances[coords.getY()][coords.getX()], distances, grid, soldierGrid, queue);
        }

        return distances;
    }

    private static void setDistance(final int y, final int x, final int prevDistance, final int[][] distances,
            final char[][] grid, final Soldier[][] soldierGrid, final PriorityQueue<Coords> queue) {
        if (grid[y][x] != '.' || soldierGrid[y][x] != null) {
            return;
        }

        if (distances[y][x] < Integer.MAX_VALUE) {
            return;
        }

        distances[y][x] = prevDistance + 1;
        queue.add(new Coords(y, x));
    }
}

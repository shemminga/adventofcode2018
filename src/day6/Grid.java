package day6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Grid {
    private final GridPoint[][] grid;

    private Grid(final int width, final int height) {
        grid = new GridPoint[width][height];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new GridPoint();
            }
        }
    }

    private void add(final Point point) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                final GridPoint gridPoint = grid[i][j];
                final int distance = Math.abs(point.getX() - i) + Math.abs(point.getY() - j);

                if (distance < gridPoint.getDistance()) {
                    gridPoint.setClosestPoint(point);
                    gridPoint.setMultiple(false);
                    gridPoint.setDistance(distance);
                } else if (distance == gridPoint.getDistance()) {
                    gridPoint.setClosestPoint(null);
                    gridPoint.setMultiple(true);
                }
            }
        }
    }

    Map<Point, Area> getAreas() {
        final Map<Point, Area> areas = new HashMap<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                final GridPoint gridPoint = grid[i][j];

                if (gridPoint.getClosestPoint() == null) {
                    continue;
                }

                final Area area = areas.computeIfAbsent(gridPoint.getClosestPoint(), point -> new Area());
                area.incrementSize();

                if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[i].length - 1) {
                    area.setInfinite();
                }
            }
        }

        return areas;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(grid.length + " " + grid[0].length + "\n");

        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                stringBuilder.append(grid[i][j]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    static Grid of(final List<Point> points) {
        final int width = points.stream()
                .mapToInt(Point::getX)
                .max()
                .orElseThrow(AssertionError::new) + 1;
        final int height = points.stream()
                .mapToInt(Point::getY)
                .max()
                .orElseThrow(AssertionError::new) + 1;

        System.out.println(width + " " + height);
        final Grid grid = new Grid(width, height);
        points.forEach(grid::add);
        return grid;
    }

    static final class GridPoint {
        private Point closestPoint = null;
        private int distance = Integer.MAX_VALUE;
        private boolean multiple = false;

        private Point getClosestPoint() {
            return closestPoint;
        }

        private void setClosestPoint(final Point closestPoint) {
            this.closestPoint = closestPoint;
        }

        private int getDistance() {
            return distance;
        }

        private void setDistance(final int distance) {
            this.distance = distance;
        }

        private boolean isMultiple() {
            return multiple;
        }

        private void setMultiple(final boolean multiple) {
            this.multiple = multiple;
        }

        @Override
        public String toString() {
            if (multiple) {
                return ".";
            }

            if (distance == 0) {
                return String.valueOf(closestPoint.getId());
            }

            return String.valueOf(closestPoint.getId()).toLowerCase();
        }
    }

    static final class Area {
        private boolean infinite = false;
        private int size = 0;

        private void setInfinite() {
            infinite = true;
        }

        boolean isInfinite() {
            return infinite;
        }

        private void incrementSize() {
            size++;
        }

        int getSize() {
            return size;
        }

        @Override
        public String toString() {
            return "Area{" + "infinite=" + infinite + ", size=" + size + '}';
        }

    }
}

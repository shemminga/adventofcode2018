package day11;

final class FuelCellGridB {
    private final int[][]grid = new int[301][301];

    FuelCellGridB(final int serialNo) {
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                grid[i][j] = PowerLevelCalculator.calculatePowerLevel(i, j, serialNo);
            }
        }
    }

    Square getMaxSquare() {
        int maxX = 0;
        int maxY = 0;
        int maxW = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                for (int w = 1; w < getMaxWidth(i, j); w++) {
                    final int currSum = getSum(i, j, w);

                    if (currSum > maxSum) {
                        maxX = i;
                        maxY = j;
                        maxW = w;
                        maxSum = currSum;
                    }
                }
            }
        }

        return new Square(maxX, maxY, maxW, maxSum);
    }

    private int getMaxWidth(final int x, final int y) {
        return Math.min(grid.length - x, grid[x].length - y);
    }

    private int getSum(final int x, final int y, final int size) {
        int total = 0;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                total += grid[i][j];
            }
        }
        return total;
    }
}

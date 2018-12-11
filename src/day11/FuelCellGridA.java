package day11;

import java.util.AbstractMap;
import java.util.Map;

final class FuelCellGridA {
    private final int[][] grid = new int[301][301];

    FuelCellGridA(final int serialNo) {
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                grid[i][j] = PowerLevelCalculator.calculatePowerLevel(i, j, serialNo);
            }
        }
    }

    int get(final int x, final int y) {
        return grid[x][y];
    }

    Map.Entry<Integer, Integer> getMax33Square() {
        int maxX = 0;
        int maxY = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 2; i < grid.length - 1; i++) {
            for (int j = 2; j < grid[i].length - 1; j++) {
                final int currSum = grid[i - 1][j - 1] + grid[i - 1][j] + grid[i - 1][j + 1] +
                        grid[i][j - 1] + grid[i][j] + grid[i][j + 1] +
                        grid[i + 1][j - 1] + grid[i + 1][j] + grid[i + 1][j + 1];

                if (currSum > maxSum) {
                    maxX = i;
                    maxY = j;
                    maxSum = currSum;
                }
            }
        }

        return new AbstractMap.SimpleImmutableEntry<>(maxX - 1, maxY - 1);
    }
}

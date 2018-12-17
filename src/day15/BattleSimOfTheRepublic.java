package day15;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

final class BattleSimOfTheRepublic {
    private final char[][] grid;
    private final List<Soldier> soldiers = new ArrayList<>();
    private int completedRounds = 0;

    BattleSimOfTheRepublic(final char[][] inputGrid) {
        this.grid = GridPrinter.copyGrid(inputGrid);

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] != '.' && grid[y][x] != '#') {
                    final SoldierType soldierType = SoldierType.ofChar(grid[y][x]);
                    grid[y][x] = '.';
                    soldiers.add(new Soldier(y, x, soldierType));
                }
            }
        }
    }

    void setElfAttackPower(final int newAttackPower) {
        soldiers.stream()
                .filter(soldier -> soldier.getType() == SoldierType.ELF)
                .forEach(soldier -> soldier.setAttackPower(newAttackPower));
    }

    long combat() {
        final long elfCountBefore = soldiers.stream()
                .filter(soldier -> soldier.getType() == SoldierType.ELF)
                .count();

//        GridPrinter.printGrid(0, grid, soldiers);
        while (multipleSoldierTypes()) {
            final boolean endRoundEarly = oneBattleRound();
//            GridPrinter.printGrid(completedRounds + 1, grid, soldiers);

            if (endRoundEarly) {
                break;
            }

            soldiers.sort(Comparator.comparingInt(Soldier::getY).thenComparingInt(Soldier::getX));
            completedRounds++;
        }

        ResultPrinter.printResult(completedRounds, soldiers);

        final long elfCountAfter = soldiers.stream()
                .filter(soldier -> soldier.getType() == SoldierType.ELF)
                .count();

        return elfCountBefore - elfCountAfter;
    }

    private boolean multipleSoldierTypes() {
        return soldiers.stream()
                .filter(Soldier::isAlive)
                .map(Soldier::getType)
                .distinct()
                .count() == 2;
    }

    private boolean oneBattleRound() {
        for (int i = 0; i < soldiers.size(); i++) {
            if (soldiers.get(i).isAlive()) {
                doTurn(soldiers.get(i));
            }

            if (i < soldiers.size() - 1) {
                if (!multipleSoldierTypes()) {
                    return true;
                }
            }
        }

        soldiers.removeIf(Soldier::isDead);
        return false;
    }

    private void doTurn(final Soldier soldier) {
        SoldierMover.move(soldier, grid, soldiers);
        SoldierFightHelper.letAttack(soldier, soldiers);
    }
}

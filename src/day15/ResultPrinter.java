package day15;

import java.util.List;

final class ResultPrinter {
    private ResultPrinter() {
    }

    static void printResult(final int completedRounds, final List<Soldier> soldiers) {
        if (soldiers.get(0)
                .getType() == SoldierType.ELF) {
            System.out.println("Glory, glory, hallelujah!");
            System.out.println("His Elfs are marching on!");
        } else {
            System.out.println("Goblin, goblin, what a hell of a way die!");
        }

        final int totalHitpointsLeft = totalHitpointsLeft(soldiers);

        System.out.println();
        System.out.println("Completed rounds: " + completedRounds);
        System.out.println("Total hitpoints left: " + totalHitpointsLeft);
        System.out.println("Result: " + (completedRounds * totalHitpointsLeft));
    }

    private static int totalHitpointsLeft(final List<Soldier> soldiers) {
        return soldiers.stream()
                .mapToInt(Soldier::getHitpoints)
                .filter(x -> x > 0)
                .sum();
    }
}

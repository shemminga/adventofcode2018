package day15;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

final class Day15b {
    private Day15b() {
    }

    public static void main(final String... args) throws URISyntaxException, IOException {
        final URL input = Day15b.class.getResource("input.txt");
        final List<String> lines = Files.readAllLines(Paths.get(input.toURI()));
        final char[][] grid = lines.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        int attackPower = 3;
        long elfDeathToll = Long.MAX_VALUE;
        while (elfDeathToll > 0) {
            attackPower++;

            final BattleSimOfTheRepublic sim = new BattleSimOfTheRepublic(grid);
            sim.setElfAttackPower(attackPower);
            elfDeathToll = sim.combat();
            System.out.println("Attack power: " + attackPower + " Elf death toll: " + elfDeathToll);
            System.out.println();
            System.out.println();
        }
    }
}

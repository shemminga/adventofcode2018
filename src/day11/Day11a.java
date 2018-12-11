package day11;

import java.util.Map;

final class Day11a {
    private Day11a() {
    }

    public static void main(final String... args) {
        findMax33Square(18);
        findMax33Square(42);
        findMax33Square(8772);
    }

    private static void findMax33Square(final int serialNo) {
        final Map.Entry<Integer, Integer> max33Square = new FuelCellGridA(serialNo).getMax33Square();
        System.out.println(serialNo + " -> " + max33Square.getKey() + ',' + max33Square.getValue());
    }
}
